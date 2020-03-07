package cn.bupt.edu.handler;

import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecodeHandler extends ChannelInboundHandlerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(DecodeHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf resp = (ByteBuf) msg;
        byte[] rb = new byte[resp.readableBytes()];
        resp.readBytes(rb);
        ProtocolResqMsgProto.ProtocolRespMsg resqmsg = ProtocolResqMsgProto.ProtocolRespMsg.parseFrom(rb);
        logger.info("uuid = {} clientReceive chains = {}", resqmsg.getUuid(), resqmsg.getChainList());
        ctx.fireChannelRead(resqmsg);
    }
}
