package cn.bupt.edu.server.netty.handler;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.base.util.LogInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ProtocolStringList;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncodeHandler extends ChannelOutboundHandlerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(EncodeHandler.class);
    private static JSONObject jresp = new JSONObject();

    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ProtocolResqMsgProto.ProtocolRespMsg) {
            ProtocolResqMsgProto.ProtocolRespMsg resp = (ProtocolResqMsgProto.ProtocolRespMsg) msg;

            jresp.put(LogInfo.UUID, resp.getUuid());
            jresp.put(LogInfo.LOGO, LogInfo.SERVER_ENCODING_START);
            logger.info(jresp.toJSONString());

            byte[] rb = resp.toByteArray();

            jresp.put(LogInfo.LOGO, LogInfo.SERVER_ENCODING_END);
            jresp.put(LogInfo.PROTOCOL_MSG_SIZE, rb.length);
            logger.info(jresp.toJSONString());

            ByteBuf buf = Unpooled.buffer(rb.length);
            buf.writeBytes(rb);
            buf.writeBytes(Const.DELIMITER);
            ctx.write(buf, promise);
            //接收日志输出
            jresp.put(LogInfo.LOGO, LogInfo.SERVER_RESP);
            JSONArray chains = new JSONArray();
            ProtocolStringList cl = resp.getChainList();
            for (int i = 0; i < cl.size(); i++) {
                chains.add(cl.get(i));
            }
            jresp.put(LogInfo.CHAINS, chains);
            jresp.put(LogInfo.STATUS, resp.getStatus());

            logger.info(jresp.toJSONString());
        } else {
            ctx.write(msg, promise);
        }
    }
}
