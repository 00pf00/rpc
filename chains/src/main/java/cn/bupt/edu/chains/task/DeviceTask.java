package cn.bupt.edu.chains.task;

import cn.bupt.edu.chains.entity.DeviceInfoProto;
import cn.bupt.edu.server.anotate.TaskMapping;
import cn.bupt.edu.server.task.DefaultTaskServer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.stereotype.Component;

@Component
@TaskMapping(paths = {"/restchains/save", "/chains/chaininfo"})
public class DeviceTask extends DefaultTaskServer {
    @Override
    protected Object Decoding(ByteString rb) throws InvalidProtocolBufferException {
        return DeviceInfoProto.DeviceInfo.parseFrom(rb);
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
