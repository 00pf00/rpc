package cn.bupt.edu.netty.handler;

import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class EncodeHandler extends ChannelOutboundHandlerAdapter {
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ProtocolResqMsgProto.ProtocolRespMsg) {
            ProtocolResqMsgProto.ProtocolRespMsg resp = (ProtocolResqMsgProto.ProtocolRespMsg) msg;
            byte[] rb = resp.toByteArray();
            ByteBuf buf = Unpooled.buffer(rb.length);
            buf.writeBytes(rb);
            ctx.write(buf, promise);
        } else {
            ctx.write(msg, promise);
        }
    }
}
