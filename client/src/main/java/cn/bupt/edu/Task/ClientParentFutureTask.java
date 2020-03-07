package cn.bupt.edu.Task;

import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.task.client.ClientTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ClientParentFutureTask extends FutureTask<Object> {

    private Callable<Object> clientTask;

    public ClientParentFutureTask(Callable<Object> callable) {
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
