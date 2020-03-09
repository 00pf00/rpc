package cn.bupt.edu.base.handler;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncodeHandler extends ChannelOutboundHandlerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(EncodeHandler.class);

    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ProtocolReqMsgProto.ProtocolReqMsg) {
            ProtocolReqMsgProto.ProtocolReqMsg req = (ProtocolReqMsgProto.ProtocolReqMsg) msg;
            byte[] rb = req.toByteArray();
            ByteBuf buf = Unpooled.buffer(rb.length);
            buf.writeBytes(rb);
            ctx.write(buf, promise);
            logger.info("uuid = {} clientReq path = {} ", req.getUuid(), req.getPath());
        } else {
            ctx.write(msg, promise);
        }
    }
}
