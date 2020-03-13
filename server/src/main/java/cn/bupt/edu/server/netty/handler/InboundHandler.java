package cn.bupt.edu.server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InboundHandler extends ChannelInboundHandlerAdapter {
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("inbound 异常捕获事件\n");
        ctx.fireExceptionCaught(cause);
    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("inbound 自定义事件\n");
        ctx.fireUserEventTriggered(evt);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("inbound inactive \n");
        ctx.fireChannelRead(msg);
    }

    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("inbound channelWritabilityChanged \n");
        ctx.fireChannelWritabilityChanged();
    }

}
