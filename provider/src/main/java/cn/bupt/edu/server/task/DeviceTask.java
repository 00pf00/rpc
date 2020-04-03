package cn.bupt.edu.server.task;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.server.anotate.TaskMapping;
import cn.bupt.edu.server.entity.DeviceInfoProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@TaskMapping(paths = {"/device/deviceinfo"})
public class DeviceTask extends DefaultServerTask {
    protected DeviceTask(ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        super(req, ctx);
    }

    public DeviceTask() {
    }
    @Override
    protected Object[] Decoding(ByteString byteString, Method method) {

        try {
            return new Object[]{DeviceInfoProto.DeviceInfo.parseFrom(byteString)};
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected byte[] Encoding(Object obj) {
        if (obj instanceof DeviceInfoProto.DeviceInfo) {
            DeviceInfoProto.DeviceInfo resp = (DeviceInfoProto.DeviceInfo) obj;
            return resp.toByteArray();
        }
        return null;
    }
}
