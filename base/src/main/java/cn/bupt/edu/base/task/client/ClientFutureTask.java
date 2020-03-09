package cn.bupt.edu.base.task.client;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.task.ParentFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ClientFutureTask extends FutureTask<Object> implements ParentFutureTask {

    private Callable<Object> clientTask;

    public ClientFutureTask(Callable<Object> callable) {
        super(callable);
        this.clientTask = callable;
    }

    public void setResp(ProtocolResqMsgProto.ProtocolRespMsg resp) {
        ClientTask ct = getClientTask();
        if (ct != null) {
            ct.setResp(resp);
        }

    }

    public ClientTask getClientTask() {
        if (clientTask instanceof ClientTask) {
            return (ClientTask) clientTask;
        }
        return null;
    }

    @Override
    public void initThread() {
        ClientTask ct = getClientTask();
        if (ct != null) {
            ct.initThread();
        }
    }
}
