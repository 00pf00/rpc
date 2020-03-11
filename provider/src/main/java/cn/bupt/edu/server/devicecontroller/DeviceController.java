package cn.bupt.edu.server.devicecontroller;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.task.client.ClientFutureTask;
import cn.bupt.edu.base.task.client.ClientTask;
import cn.bupt.edu.base.thread.ParentThread;
import cn.bupt.edu.client.datadispatch.ClientTaskMap;
import cn.bupt.edu.server.channel.RpcClient;
import cn.bupt.edu.server.controller.HandlerController;
import cn.bupt.edu.server.entity.DeviceInfoProto;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller("device")
public class DeviceController extends HandlerController {
    @GetMapping("/deviceinfo")
    @ResponseBody
    public byte[] getDeviceInfo(ByteString device) throws Exception {
        DeviceInfoProto.DeviceInfo deviceInfo = DeviceInfoProto.DeviceInfo.parseFrom(device);
        this.getLog().info("device name = {}", deviceInfo.getName());
        DeviceInfoProto.DeviceInfo.Builder deviceBuilder = DeviceInfoProto.DeviceInfo.newBuilder();
        deviceBuilder.setId(UUID.randomUUID().toString());
        deviceBuilder.setName("device-2");
        deviceBuilder.setTemperature(100);
        ProtocolReqMsgProto.ProtocolReqMsg.Builder reqBuilder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
        String uuid = "";
        Thread t = Thread.currentThread();
        if (t instanceof ParentThread) {
            ParentThread pt = (ParentThread) t;
            uuid = pt.getUuid();
        }
        reqBuilder.setUuid(uuid);
        reqBuilder.setVersion(1);
        reqBuilder.addChain("chains");
        reqBuilder.setPath("/chains/chaininfo");
        reqBuilder.setBody(ByteString.copyFrom(deviceBuilder.build().toByteArray()));
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
        if (obj instanceof DeviceInfoProto.DeviceInfo) {
            DeviceInfoProto.DeviceInfo resp = (DeviceInfoProto.DeviceInfo) obj;
            this.getLog().info("device name = " + resp.getName());
            return resp.toByteArray();
        }
        return deviceBuilder.build().toByteArray();
    }

}
