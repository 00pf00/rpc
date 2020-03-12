package cn.bupt.edu.server.netty.handler;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.base.util.Status;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        if (msg instanceof ProtocolReqMsgProto.ProtocolReqMsg) {
            ProtocolReqMsgProto.ProtocolReqMsg req = (ProtocolReqMsgProto.ProtocolReqMsg) msg;
            if (req.getPath().equals(Const.REQ_HEARTBEAT)) {
                ProtocolResqMsgProto.ProtocolRespMsg.Builder builder = ProtocolResqMsgProto.ProtocolRespMsg.newBuilder();
                builder.setStatus(Status.STATUS_HEARTBEAT);
                ctx.writeAndFlush(builder.build());
            } else {
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }

    }

//    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
//        ctx.channel().eventLoop().scheduleAtFixedRate(new HeartBeatHandler.HeartBeatTask(ctx), 0, 1, TimeUnit.SECONDS);
//    }

//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        System.out.println("Channel heartBeat lost");
//        if (evt instanceof IdleStateEvent) {
//            System.out.println(((IdleStateEvent) evt).state());
//            //ctx.close();
//        }
//    }

//    class HeartBeatTask implements Runnable {
//        private ChannelHandlerContext ctx;
//
//        public HeartBeatTask(ChannelHandlerContext cctx) {
//            this.ctx = cctx;
//        }
//
//        @Override
//        public void run() {
//            ByteBuf buf = Unpooled.buffer(1);
//            buf.writeBytes(new byte[]{'d'});
//            ctx.writeAndFlush(buf);
//        }
//    }
}
