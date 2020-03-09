package cn.bupt.edu.base.task.client;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.task.AbstractParentTask;
import cn.bupt.edu.base.task.ParentTask;
import cn.bupt.edu.base.thread.ParentThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public abstract class ClientTask extends AbstractParentTask implements Callable<Object>, ParentTask {
    private final static Logger logger = LoggerFactory.getLogger(ClientTask.class);
    private ProtocolResqMsgProto.ProtocolRespMsg resp;
    private String[] chains;

    public ClientTask() {
        ParentThread pt = getThread();
        if (pt != null) {
            this.chains = pt.getChains();
        } else {
            chains = new String[3];
        }

    }

    public void initThread() {
        ParentThread pt = getThread();
        if (pt != null) {
            //设置服务调用链chains
            for (int i = 0; i < this.resp.getChainCount(); i++) {
                for (int j = 0; j < chains.length; j++) {
                    if (chains[j] != null) {
                        continue;
                    }
                    this.chains[j] = this.resp.getChain(i);
                    break;
                }
            }
            //设置uuid
            pt.setUuid(this.resp.getUuid());
            pt.setName("serverThreadPool-" + this.resp.getUuid());
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public ProtocolResqMsgProto.ProtocolRespMsg getResp() {
        return resp;
    }

    public void setResp(ProtocolResqMsgProto.ProtocolRespMsg resp) {
        this.resp = resp;
    }

    public ParentThread getThread() {
        return super.getThread();
    }
}
