package cn.bupt.edu.base.devicecontroller;

import cn.bupt.edu.base.controller.HandlerController;
import cn.bupt.edu.base.entity.DeviceInfoProto;
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
        return deviceBuilder.build().toByteArray();
    }

}
