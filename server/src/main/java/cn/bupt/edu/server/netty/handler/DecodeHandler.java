package cn.bupt.edu.server.netty.handler;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.util.LogInfo;
import cn.bupt.edu.base.util.RPCUUID;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecodeHandler extends ChannelInboundHandlerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(DecodeHandler.class);
    private static JSONObject jreq = new JSONObject();

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf resp = (ByteBuf) msg;
        byte[] rb = new byte[resp.readableBytes()];
        resp.readBytes(rb);

        jreq.put(LogInfo.LOGO, LogInfo.SERVER_DECODING_START);
        jreq.put(LogInfo.DECODING_UUID, RPCUUID.getUUID());
        jreq.put(LogInfo.PROTOCOL_MSG_SIZE, rb.length);
        logger.info(jreq.toJSONString());
        ProtocolReqMsgProto.ProtocolReqMsg req = ProtocolReqMsgProto.ProtocolReqMsg.parseFrom(rb);
        jreq.put(LogInfo.LOGO, LogInfo.SERVER_DECODING_END);
        jreq.put(LogInfo.UUID, req.getUuid());
        logger.info(jreq.toJSONString());
        //server端接收日志
        jreq.remove(LogInfo.DECODING_UUID);
        jreq.put(LogInfo.UUID, req.getUuid());
        jreq.put(LogInfo.PATH, req.getPath());
        jreq.put(LogInfo.LOGO, LogInfo.SERVER_RECV);
        logger.info(jreq.toJSONString());
        ctx.fireChannelRead(req);
    }
}
