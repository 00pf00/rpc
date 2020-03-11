package cn.bupt.edu.client.handler;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.base.util.LogInfo;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncodeHandler extends ChannelOutboundHandlerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(EncodeHandler.class);
    private static JSONObject jreq = new JSONObject();

    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ProtocolReqMsgProto.ProtocolReqMsg) {
            ProtocolReqMsgProto.ProtocolReqMsg req = (ProtocolReqMsgProto.ProtocolReqMsg) msg;

            jreq.put(LogInfo.UUID, req.getUuid());
            jreq.put(LogInfo.LOGO, LogInfo.CLIENT_ENCODING_START);
            logger.info(jreq.toJSONString());

            byte[] rb = req.toByteArray();

            jreq.put(LogInfo.PROTOCOL_MSG_SIZE, rb.length);
            jreq.put(LogInfo.LOGO, LogInfo.CLIENT_ENCODING_END);
            logger.info(jreq.toJSONString());

            ByteBuf buf = Unpooled.buffer(rb.length + 1);
            buf.writeBytes(rb);
            buf.writeBytes(Const.DELIMITER);
            ctx.write(buf, promise);

            jreq.put(LogInfo.PATH, req.getPath());
            jreq.put(LogInfo.LOGO, LogInfo.CLIENT_REQ);
            logger.info(jreq.toJSONString());
        } else {
            ctx.write(msg, promise);
        }
    }
}
