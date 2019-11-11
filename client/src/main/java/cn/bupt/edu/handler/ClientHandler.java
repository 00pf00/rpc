package cn.bupt.edu.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf firstMessage;

    public ClientHandler(){
        firstMessage = Unpooled.buffer(100);
        for (int i = 0; i < firstMessage.capacity();i ++){
            firstMessage.writeByte((byte) i);
        }
    }

    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush(firstMessage);
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println(msg);
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
