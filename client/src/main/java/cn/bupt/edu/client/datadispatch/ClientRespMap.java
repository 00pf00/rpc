package cn.bupt.edu.client.datadispatch;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;

import java.util.concurrent.ConcurrentHashMap;

public class ClientRespMap {
    private static ClientRespMap clientRespMap = new ClientRespMap();
    private ConcurrentHashMap<String, ProtocolResqMsgProto.ProtocolRespMsg> respMap = new ConcurrentHashMap<>();

    public static ClientRespMap getInstance() {
        return clientRespMap;
    }

    public ProtocolResqMsgProto.ProtocolRespMsg getResp(String uuid) {
        return respMap.get(uuid);
    }

    public void addResp(String uuid, ProtocolResqMsgProto.ProtocolRespMsg resp) {
        respMap.put(uuid, resp);
    }

}
