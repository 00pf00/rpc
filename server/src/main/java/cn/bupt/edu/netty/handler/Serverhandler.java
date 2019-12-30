package cn.bupt.edu.netty.handler;


import cn.bupt.edu.netty.consumer.QueueConsumer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Serverhandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        ByteBuf buf = (ByteBuf)msg;
        int len = buf.readableBytes();
        byte[] cli = new byte[len];
        buf.readBytes(cli);
        System.out.println(new String(cli));
        ByteBuf server = Unpooled.buffer(1);
        server.writeBytes(new byte[]{'b'});
        ctx.write(server);
        //Channel ch = ctx.channel();
        //QueueConsumer.channels.add(ch);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
