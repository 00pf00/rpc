package cn.bupt.edu.datadispatch;

import cn.bupt.edu.protocol.ProtocolResqMsgProto;

import java.util.concurrent.ConcurrentHashMap;

public class ClientResp {
    private static ClientResp clientResp;
    private ConcurrentHashMap<String, ProtocolResqMsgProto.ProtocolRespMsg> respMap;

    public static ClientResp getInstance() {
        if (clientResp == null) {
            clientResp = new ClientResp();
            clientResp.respMap = new ConcurrentHashMap<>();
        }
        return clientResp;
    }

    public ProtocolResqMsgProto.ProtocolRespMsg getResp(String uuid) {
        return respMap.get(uuid);
    }

    public void addResp(String uuid, ProtocolResqMsgProto.ProtocolRespMsg resp) {
        respMap.put(uuid, resp);
    }

}
