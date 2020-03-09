package cn.bupt.edu.base.chainscontroller;

import cn.bupt.edu.base.controller.HandlerController;
import com.google.protobuf.ByteString;
import entity.DeviceInfoProto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller("chains")
public class ChainsController extends HandlerController {

    @GetMapping("/chaininfo")
    @ResponseBody
    public byte[] getChainInfo(ByteString device) throws Exception {
        DeviceInfoProto.DeviceInfo deviceInfo = DeviceInfoProto.DeviceInfo.parseFrom(device);
        this.getLog().info("device name = {}", deviceInfo.getName());
        DeviceInfoProto.DeviceInfo.Builder deviceBuilder = DeviceInfoProto.DeviceInfo.newBuilder();
        deviceBuilder.setId(UUID.randomUUID().toString());
        deviceBuilder.setName("device-3");
        deviceBuilder.setTemperature(100);
        return deviceBuilder.build().toByteArray();
    }
}
