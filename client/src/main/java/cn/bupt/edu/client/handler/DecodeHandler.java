package cn.bupt.edu.client.handler;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.util.LogInfo;
import cn.bupt.edu.base.util.RPCUUID;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ProtocolStringList;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecodeHandler extends ChannelInboundHandlerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(DecodeHandler.class);
    private static JSONObject jresp = new JSONObject();

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf resp = (ByteBuf) msg;
        byte[] rb = new byte[resp.readableBytes()];
        resp.readBytes(rb);

        jresp.put(LogInfo.LOGO, LogInfo.CLIENT_DECODING_START);
        jresp.put(LogInfo.DECODING_UUID, RPCUUID.getUUID());
        jresp.put(LogInfo.PROTOCOL_MSG_SIZE,rb.length);
        logger.info(jresp.toJSONString());

        ProtocolResqMsgProto.ProtocolRespMsg respmsg = ProtocolResqMsgProto.ProtocolRespMsg.parseFrom(rb);

        jresp.put(LogInfo.LOGO, LogInfo.CLIENT_DECODING_END);
        jresp.put(LogInfo.UUID, respmsg.getUuid());
        logger.info(jresp.toJSONString());

        //接收日志输出
        jresp.put(LogInfo.UUID, respmsg.getUuid());
        jresp.put(LogInfo.LOGO, LogInfo.CLIENT_RECV);
        JSONArray chains = new JSONArray();
        ProtocolStringList cl = respmsg.getChainList();
        for (int i = 0; i < cl.size(); i++) {
            chains.add(cl.get(i));
        }
        jresp.put(LogInfo.CHAINS, chains);
        jresp.put(LogInfo.STATUS, respmsg.getStatus());
        logger.info(jresp.toJSONString());
        ctx.fireChannelRead(respmsg);
    }
}
