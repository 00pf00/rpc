package cn.bupt.edu.handler;

import cn.bupt.edu.context.data.DataContext;
import cn.bupt.edu.dns.ClusterIp;
import cn.bupt.edu.protocol.ProtocolReqMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;

import java.util.concurrent.TimeUnit;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    private int count;
    private String port;
    private String version;
    private String serviceName;

    public HeartBeatHandler(int end) {
        this.count = end;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
        ctx.channel().eventLoop().scheduleAtFixedRate(new HeartBeatHandler.HeartBeatTask(ctx, this.version), 0, 1, TimeUnit.SECONDS);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {

        ByteBuf buf = (ByteBuf) msg;
        int len = buf.readableBytes();
        byte[] server = new byte[len];
        buf.readBytes(server);
        System.out.println(new String(server));
        if (count < 0) {
            ctx.channel().eventLoop().shutdownGracefully();
            ctx.close();
        }
        count = count - 1;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof ReadTimeoutException) {
            System.out.println("-------------read timeout-----------------\n");
        } else {
            ctx.fireExceptionCaught(cause);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //释放Client端处理响应的Task任务的缓存
        DataContext.getInstance().ResetModule(this.serviceName);
        if (evt instanceof IdleStateEvent) {
            reconnect(ClusterIp.GetClusterIP(), this.port);
        } else {
            ctx.close();
        }
    }

    private void reconnect(String p, String i) {

    }

    class HeartBeatTask implements Runnable {
        private ChannelHandlerContext ctx;
        private String version;

        public HeartBeatTask(ChannelHandlerContext cctx, String v) {
            this.ctx = cctx;
            this.version = v;
        }

        @Override
        public void run() {
            ProtocolReqMsg req = new ProtocolReqMsg();
            req.setVersion(this.version);
            byte[] rb = req.Encode();
            ByteBuf buf = Unpooled.buffer(rb.length);
            buf.writeBytes(rb);
            ctx.writeAndFlush(buf);
        }
    }
}
