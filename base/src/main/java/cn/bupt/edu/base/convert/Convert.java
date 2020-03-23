package cn.bupt.edu.base.convert;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class Convert {
    public static <T> T toPojo(Message msg, Class<T> clazz) {
        try {
            String js = com.google.protobuf.util.JsonFormat.printer().print(msg);
            return JSON.parseObject(js, clazz);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Message toProtobuf(Object obj, Message.Builder builder) {
        String js = JSON.toJSONString(obj);
        try {
            JsonFormat.parser().merge(js, builder);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
}
