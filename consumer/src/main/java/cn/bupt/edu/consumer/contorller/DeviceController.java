package cn.bupt.edu.consumer.contorller;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.task.client.ClientFutureTask;
import cn.bupt.edu.base.task.client.ClientTask;
import cn.bupt.edu.base.util.RPCUUID;
import cn.bupt.edu.client.datadispatch.ClientTaskMap;
import cn.bupt.edu.consumer.channel.RpcClient;
import cn.bupt.edu.consumer.entity.DeviceInfoProto;
import com.google.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceController {
    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @GetMapping(value = "/device")
    @ResponseBody
    public byte[] getDeviceIn() throws Exception {
        cn.bupt.edu.consumer.entity.DeviceInfoProto.DeviceInfo.Builder builder = cn.bupt.edu.consumer.entity.DeviceInfoProto.DeviceInfo.newBuilder();
        builder.setId(RPCUUID.getUUID());
        builder.setName("device-1");
        builder.setTemperature(20);
        byte[] device = builder.build().toByteArray();
        ProtocolReqMsgProto.ProtocolReqMsg.Builder reqBuilder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
        String uuid = RPCUUID.getUUID();
        reqBuilder.setUuid(uuid);
        reqBuilder.setVersion(1);
        reqBuilder.addChain("device");
        reqBuilder.setPath("/device/deviceinfo");
//        reqBuilder.addChain("chains");
//        reqBuilder.setPath("/chains/chaininfo");
        reqBuilder.setBody(ByteString.copyFrom(device));
        ProtocolReqMsgProto.ProtocolReqMsg req = reqBuilder.build();
        RpcClient.getChannel().writeAndFlush(req);
        ClientTask tc = new ClientTask() {
            @Override
            public Object call() throws Exception {
                DeviceInfoProto.DeviceInfo device = DeviceInfoProto.DeviceInfo.parseFrom(this.getResp().getBody());
                this.getLogger().info("device name = {}", device.getName());
                return device;
            }
        };
        ClientFutureTask pf = new ClientFutureTask(tc);
        ClientTaskMap.getInstance().putTask(uuid, pf);
        Object obj = pf.get();
        if (obj instanceof cn.bupt.edu.consumer.entity.DeviceInfoProto.DeviceInfo) {
            cn.bupt.edu.consumer.entity.DeviceInfoProto.DeviceInfo resp = (DeviceInfoProto.DeviceInfo) obj;
            logger.info("device name = {}", resp.getName());
            return resp.toByteArray();
        }
        return null;
    }
}
