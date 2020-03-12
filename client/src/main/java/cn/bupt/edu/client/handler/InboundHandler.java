package cn.bupt.edu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InboundHandler extends ChannelInboundHandlerAdapter {


    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("inbound 自定义事件\n");
        if (evt instanceof String) {
            System.out.println(evt);
        }
        ctx.fireUserEventTriggered(evt);
    }


}
