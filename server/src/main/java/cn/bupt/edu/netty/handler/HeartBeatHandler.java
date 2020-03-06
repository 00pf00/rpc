package cn.bupt.edu.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.TimeUnit;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    private int count;

    public HeartBeatHandler(int end) {
        this.count = end;
    }

    class HeartBeatTask implements Runnable {
        private ChannelHandlerContext ctx;

        public HeartBeatTask(ChannelHandlerContext cctx) {
            this.ctx = cctx;
        }

        @Override
        public void run() {
            ByteBuf buf = Unpooled.buffer(1);
            buf.writeBytes(new byte[]{'d'});
            ctx.writeAndFlush(buf);
        }
    }

//    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {
//        ctx.channel().eventLoop().scheduleAtFixedRate(new HeartBeatHandler.HeartBeatTask(ctx), 0, 1, TimeUnit.SECONDS);
//    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        ByteBuf buf = (ByteBuf) msg;
        int len = buf.readableBytes();
        byte[] cli = new byte[len];
        buf.readBytes(cli);
        System.out.println(new String(cli));
//        if (server[0] == 'e') {
//            System.out.println(new String(server));
//            System.out.println("----------receive heartbeat-----------------\n");
//            if (this.count > 0) {
//                ByteBuf bbuf = Unpooled.buffer(1);
//                bbuf.writeBytes(new byte[]{'d'});
//                ctx.channel().writeAndFlush(bbuf);
//            }
//            //ctx.executor().schedule(new HeartBeatHandler.HeartBeatTask(ctx),1, TimeUnit.SECONDS);
//
//            this.count = this.count - 1;
//        } else {
//            ctx.fireChannelRead(buf.writeBytes(server));
//        }
    }

    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    //    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        System.out.println("&&&&&&&&&&&&&&&\n");
//        cause.printStackTrace();
//        ctx.close();
//    }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("Channel heartBeat lost");
        if (evt instanceof IdleStateEvent) {
            System.out.println(((IdleStateEvent) evt).state());
            //ctx.close();
        }
    }
}
