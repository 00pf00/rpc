package cn.bupt.edu.client.handler;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.client.ChannelClinet;
import cn.bupt.edu.client.clientmanagement.ClientManagement;
import cn.bupt.edu.client.clientmanagement.RpcClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
        ctx.channel().eventLoop().scheduleAtFixedRate(new HeartBeatHandler.HeartBeatTask(ctx), 0, 5, TimeUnit.SECONDS);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("inbound 异常捕获事件\n");
        ChannelClinet.setChannel(null);
        ctx.channel().eventLoop().shutdownGracefully();
        ctx.close();

    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress addr = ctx.channel().remoteAddress();
        String[] ips = addr.toString().substring(1).split(":");
        System.out.println(ips[0] + ips[1]);
        Channel ch = ClientManagement.getChannel(ips[0], new Integer(ips[1]).intValue());
        RpcClient client = ClientManagement.getRpcClient(ips[0], ips[1]);
        client.setChannel(ch);
        ctx.fireChannelInactive();
    }


    class HeartBeatTask implements Runnable {
        private ChannelHandlerContext ctx;
        private ProtocolReqMsgProto.ProtocolReqMsg req;

        public HeartBeatTask(ChannelHandlerContext c) {
            this.ctx = c;
            ProtocolReqMsgProto.ProtocolReqMsg.Builder builder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
            builder.setPath(Const.REQ_HEARTBEAT);
            req = builder.build();
        }

        @Override
        public void run() {
            if (this.ctx.channel().isActive()) {
                ctx.channel().writeAndFlush(req);
            } else {
                ctx.channel().eventLoop().shutdownGracefully();
            }
        }
    }
}
