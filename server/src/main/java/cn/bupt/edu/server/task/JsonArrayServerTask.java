package cn.bupt.edu.server.task;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import com.alibaba.fastjson.JSONArray;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;

public class JsonArrayServerTask extends JsonServerTask {
    protected JsonArrayServerTask(ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        super(req, ctx);
    }

    public JsonArrayServerTask() {

    }

    @Override
    protected Object[] Decoding(ByteString rb, Method m) {
        return super.Decoding(rb, m);
    }

    @Override
    protected byte[] Encoding(Object obj) {
        return JSONArray.toJSONBytes(obj);
    }
}
