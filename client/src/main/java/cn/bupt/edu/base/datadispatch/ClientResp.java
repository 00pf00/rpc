package cn.bupt.edu.base.datadispatch;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;

import java.util.concurrent.ConcurrentHashMap;

public class ClientResp {
    private static ClientResp clientResp = new ClientResp();
    private ConcurrentHashMap<String, ProtocolResqMsgProto.ProtocolRespMsg> respMap = new ConcurrentHashMap<>();

    public static ClientResp getInstance() {
        return clientResp;
    }

    public ProtocolResqMsgProto.ProtocolRespMsg getResp(String uuid) {
        return respMap.get(uuid);
    }

    public void addResp(String uuid, ProtocolResqMsgProto.ProtocolRespMsg resp) {
        respMap.put(uuid, resp);
    }

}
