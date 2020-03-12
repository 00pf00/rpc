package cn.bupt.edu.client.handler;

import cn.bupt.edu.client.ChannelClinet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.SocketAddress;

public class InboundHandler extends ChannelInboundHandlerAdapter {

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SocketAddress addr = ctx.channel().remoteAddress();
        System.out.println(addr.toString());
        System.out.println("inbound 异常捕获事件\n");
        ChannelClinet.setChannel(null);
        ctx.channel().eventLoop().shutdownGracefully();
        ctx.close();

    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("inbound 自定义事件\n");
        ctx.fireUserEventTriggered(evt);
    }

}
