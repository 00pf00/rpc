package cn.bupt.edu.client.handler;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.util.Const;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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

//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
//
//        ByteBuf buf = (ByteBuf) msg;
//        int len = buf.readableBytes();
//        byte[] server = new byte[len];
//        buf.readBytes(server);
//        System.out.println(new String(server));
//        if (count < 0) {
//            ctx.channel().eventLoop().shutdownGracefully();
//            ctx.close();
//        }
//        count = count - 1;
//    }

//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        if (cause instanceof ReadTimeoutException) {
//            System.out.println("-------------read timeout-----------------\n");
//        } else {
//            ctx.fireExceptionCaught(cause);
//        }
//    }

//
//    private void reconnect(String p, String i) {
//
//    }

    class HeartBeatTask implements Runnable {
        private ChannelHandlerContext ctx;
        private String version;

        public HeartBeatTask(ChannelHandlerContext cctx, String v) {
            this.ctx = cctx;
            this.version = v;
        }

        @Override
        public void run() {
            ProtocolReqMsgProto.ProtocolReqMsg.Builder builder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
            builder.setPath(Const.REQ_HEARTBEAT);
            ctx.writeAndFlush(builder.build());
        }
    }
}
