package cn.bupt.edu.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {


    public void channelActive(ChannelHandlerContext ctx){
        ByteBuf buf = Unpooled.buffer(1);
        buf.writeBytes(new byte[]{'a'});
        ctx.writeAndFlush(buf);
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf buf = (ByteBuf)msg;
        int len = buf.readableBytes();
        byte[] server = new byte[len];
        buf.readBytes(server);
        System.out.println(new String(server));
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
