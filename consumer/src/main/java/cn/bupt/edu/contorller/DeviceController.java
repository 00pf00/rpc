package cn.bupt.edu.contorller;

import cn.bupt.edu.Task.ClientParentTask;
import cn.bupt.edu.Task.ParentFutureTask;
import cn.bupt.edu.channel.RpcClient;
import cn.bupt.edu.datadispatch.ClientResp;
import cn.bupt.edu.datadispatch.ClientTask;
import cn.bupt.edu.entity.DeviceInfoProto;
import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import com.google.protobuf.ByteString;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class DeviceController {
    @GetMapping(value = "/device")
    @ResponseBody
    public byte[] getDeviceIn() throws Exception{
        DeviceInfoProto.DeviceInfo.Builder builder = DeviceInfoProto.DeviceInfo.newBuilder();
        builder.setId(UUID.randomUUID().toString());
        builder.setName("device-1");
        builder.setTemperature(20);
        byte[] device = builder.build().toByteArray();
        ProtocolReqMsgProto.ProtocolReqMsg.Builder reqBuilder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
        String uuid = UUID.randomUUID().toString();
        reqBuilder.setUuid(uuid);
        reqBuilder.setVersion(1);
        reqBuilder.addChain("device");
        reqBuilder.setPath("/device/deviceinfo");
        reqBuilder.setBody(ByteString.copyFrom(device));
        ProtocolReqMsgProto.ProtocolReqMsg req = reqBuilder.build();
        RpcClient.getChannel().writeAndFlush(req);
        ClientParentTask tc = new ClientParentTask() {
            @Override
            public Object call() throws Exception {
               ProtocolResqMsgProto.ProtocolRespMsg resp =  ClientResp.getInstance().getResp(uuid);
                DeviceInfoProto.DeviceInfo device = DeviceInfoProto.DeviceInfo.parseFrom(resp.getBody());
                return device;
            }
        };
        ParentFutureTask pf = new ParentFutureTask(tc);
        ClientTask.getInstance().putTask(uuid,pf);
        Object obj = pf.get();
        if (obj instanceof DeviceInfoProto.DeviceInfo){
            DeviceInfoProto.DeviceInfo resp = (DeviceInfoProto.DeviceInfo)obj;
            System.out.println("device name = "+resp.getName()+"\n");
            return resp.toByteArray();
        }
        return null;
    }
}
