package cn.bupt.edu.server.netty.handler;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
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
        if (msg instanceof ProtocolResqMsgProto.ProtocolRespMsg) {
            ProtocolResqMsgProto.ProtocolRespMsg resp = (ProtocolResqMsgProto.ProtocolRespMsg) msg;
            byte[] rb = resp.toByteArray();
            ByteBuf buf = Unpooled.buffer(rb.length);
            buf.writeBytes(rb);
            ctx.write(buf, promise);
            logger.info("uuid = {} serverResp chains = {}", resp.getUuid(), resp.getChainList());
        } else {
            ctx.write(msg, promise);
        }
    }
}