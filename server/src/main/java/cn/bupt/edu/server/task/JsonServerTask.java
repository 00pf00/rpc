package cn.bupt.edu.server.task;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class JsonServerTask extends DefaultServerTask {
    protected JsonServerTask(ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        super(req, ctx);
    }

    public JsonServerTask() {

    }

    @Override
    protected Object[] Decoding(ByteString rb, Method m) {
        DefaultParameterNameDiscoverer dpnd = new DefaultParameterNameDiscoverer();
        JSONObject js = JSON.parseObject(rb.toString());
        String[] ts = dpnd.getParameterNames(m);
        Object[] os = new Object[ts.length];
        Parameter[] ps = m.getParameters();
        for (int i = 0; i < ts.length; i++) {
            Object obj = null;
            if (ps[i].getType().equals(String.class)) {
                obj = js.get(ts[i]).toString();
            } else {
                obj = JSON.parseObject(js.get(ts[i]).toString(), ps[i].getType());
            }
            os[i] = obj;
        }
        return os;
    }

    @Override
    protected byte[] Encoding(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
