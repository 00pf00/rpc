package cn.bupt.edu.base.convert;

import cn.bupt.edu.base.task.server.ServerTask;
import com.alibaba.fastjson.JSON;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Convert {
    private final static Logger logger = LoggerFactory.getLogger(Convert.class);
    public static <T> T toPojo(Message msg, Class<T> clazz) throws InvalidProtocolBufferException {
        String js = com.google.protobuf.util.JsonFormat.printer().print(msg);
        logger.info("toPojo json = {}",js);
        return JSON.parseObject(js, clazz);
    }

    public static Message toProtobuf(Object obj, Message.Builder builder) throws InvalidProtocolBufferException {
        String js = JSON.toJSONString(obj);
        logger.info("toProtobuf json = {}",js);
        JsonFormat.parser().merge(js, builder);
        return builder.build();
    }
}
