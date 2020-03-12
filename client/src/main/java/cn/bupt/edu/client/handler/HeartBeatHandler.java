package cn.bupt.edu.client.handler;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.client.ChannelClinet;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
        ctx.channel().eventLoop().scheduleAtFixedRate(new HeartBeatHandler.HeartBeatTask(ctx.channel()), 0, 1, TimeUnit.SECONDS);
    }


    private void reconnect() {
        ChannelFuture future = ChannelClinet.getInstance().getChannelFuture();
    }


    class HeartBeatTask implements Runnable {
        private Channel channel;

        public HeartBeatTask(Channel ch) {
            this.channel = ch;
        }

        @Override
        public void run() {
            if (channel.isActive()) {
                ProtocolReqMsgProto.ProtocolReqMsg.Builder builder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
                builder.setPath(Const.REQ_HEARTBEAT);
                channel.writeAndFlush(builder.build());
            }
            System.out.println("hb\n");
        }
    }
}
