package cn.bupt.edu.chains.chainscontroller;

import cn.bupt.edu.chains.entity.DeviceInfoProto;
import cn.bupt.edu.server.anotate.HandlerMapping;
import cn.bupt.edu.server.controller.HandlerController;
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
    @HandlerMapping(path = "/chaininfo")
    @ResponseBody
    public DeviceInfoProto.DeviceInfo getChainInfo(DeviceInfoProto.DeviceInfo deviceInfo) throws InvalidProtocolBufferException {
        logger.info("device name = {}", deviceInfo.getName());
        DeviceInfoProto.DeviceInfo.Builder deviceBuilder = DeviceInfoProto.DeviceInfo.newBuilder();
        deviceBuilder.setId(UUID.randomUUID().toString());
        deviceBuilder.setName("device-3");
        deviceBuilder.setTemperature(100);
        return deviceBuilder.build();
    }
}
