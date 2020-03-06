package cn.bupt.edu.controller;

import cn.bupt.edu.entity.DeviceInfoProto;
import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Controller("device")
public class DeviceController extends HandlerController {
    @GetMapping("/deviceinfo")
    @ResponseBody
    public byte[] getDeviceInfo(ByteString device) throws Exception {
        DeviceInfoProto.DeviceInfo deviceInfo = DeviceInfoProto.DeviceInfo.parseFrom(device);
        System.out.println("device name  = " + deviceInfo.getName() + "\n");
        DeviceInfoProto.DeviceInfo.Builder deviceBuilder = DeviceInfoProto.DeviceInfo.newBuilder();
        deviceBuilder.setId(UUID.randomUUID().toString());
        deviceBuilder.setName("device-2");
        deviceBuilder.setTemperature(100);
        return deviceBuilder.build().toByteArray();
    }

}
