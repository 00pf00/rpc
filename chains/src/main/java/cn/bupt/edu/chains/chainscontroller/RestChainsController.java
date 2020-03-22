package cn.bupt.edu.chains.chainscontroller;

import cn.bupt.edu.chains.entity.DeviceInfoProto;
import cn.bupt.edu.server.anotate.HandlerMapping;
import cn.bupt.edu.server.controller.HandlerController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("restchains")
public class RestChainsController extends HandlerController {
    @RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
    @HandlerMapping(path = "/save")
    public DeviceInfoProto.DeviceInfo saveDevice(@RequestBody DeviceInfoProto.DeviceInfo device) {
        System.out.println(device.getId());
        System.out.println(device.getName());
        DeviceInfoProto.DeviceInfo.Builder builder = DeviceInfoProto.DeviceInfo.newBuilder();
        builder.setId("a");
        builder.setName("A");
        return builder.build();
    }
}
