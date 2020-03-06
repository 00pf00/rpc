package cn.bupt.edu.handler;

import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class EncodeHandler extends ChannelOutboundHandlerAdapter {
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ProtocolReqMsgProto.ProtocolReqMsg) {
            ProtocolReqMsgProto.ProtocolReqMsg req = (ProtocolReqMsgProto.ProtocolReqMsg) msg;
            byte[] rb = req.toByteArray();
            ByteBuf buf = Unpooled.buffer(rb.length);
            buf.writeBytes(rb);
            ctx.write(buf, promise);
        } else {
            ctx.write(msg, promise);
        }
    }
}
