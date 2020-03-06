package cn.bupt.edu.netty.handler;

import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DecodeHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf resp = (ByteBuf)msg;
        byte[] rb = new byte[resp.readableBytes()];
        resp.readBytes(rb);
        ProtocolReqMsgProto.ProtocolReqMsg req = ProtocolReqMsgProto.ProtocolReqMsg.parseFrom(rb);
        ctx.fireChannelRead(req);
    }
}
