package cn.bupt.edu.chains.chainscontroller;

import cn.bupt.edu.server.anotate.Handler;
import cn.bupt.edu.server.controller.HandlerController;
import cn.bupt.edu.server.entity.DeviceInfoProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller("chains")
public class ChainsController extends HandlerController {
    private final static Logger logger = LoggerFactory.getLogger(ChainsController.class);

    @GetMapping("/chaininfo")
    @Handler(path = "/chaininfo")
    @ResponseBody
    public byte[] getChainInfo(ByteString device) throws InvalidProtocolBufferException {
        DeviceInfoProto.DeviceInfo deviceInfo = DeviceInfoProto.DeviceInfo.parseFrom(device);
        logger.info("device name = {}", deviceInfo.getName());
        DeviceInfoProto.DeviceInfo.Builder deviceBuilder = DeviceInfoProto.DeviceInfo.newBuilder();
        deviceBuilder.setId(UUID.randomUUID().toString());
        deviceBuilder.setName("device-3");
        deviceBuilder.setTemperature(100);
        return deviceBuilder.build().toByteArray();
    }
}
