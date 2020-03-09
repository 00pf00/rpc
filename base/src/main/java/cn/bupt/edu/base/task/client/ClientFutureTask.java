package cn.bupt.edu.base.task.client;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ClientFutureTask extends FutureTask<Object> {

    private Callable<Object> clientTask;

    public ClientFutureTask(Callable<Object> callable) {
        super(callable);
        this.clientTask = callable;
    }

    public void setResp(ProtocolResqMsgProto.ProtocolRespMsg resp) {
        if (clientTask instanceof ClientTask) {
            ClientTask ct = (ClientTask) clientTask;
            ct.setResp(resp);
        }
    }
}
