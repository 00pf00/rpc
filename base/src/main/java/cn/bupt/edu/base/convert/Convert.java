package cn.bupt.edu.base.convert;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class Convert {
    public static <T> T toPojo(Message msg, Class<T> clazz) throws InvalidProtocolBufferException {
        String js = com.google.protobuf.util.JsonFormat.printer().print(msg);
        return JSON.parseObject(js, clazz);
    }

    public static Message toProtobuf(Object obj, Message.Builder builder) throws InvalidProtocolBufferException {
        String js = JSON.toJSONString(obj);
        JsonFormat.parser().merge(js, builder);
        return builder.build();
    }
}
