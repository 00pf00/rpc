package cn.bupt.edu.base.task.client;

import cn.bupt.edu.base.task.AbstractParentTask;
import cn.bupt.edu.base.thread.ParentThread;
import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.task.ParentTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public abstract class ClientTask extends AbstractParentTask implements Callable<Object>, ParentTask {
    private final static Logger logger = LoggerFactory.getLogger(ClientTask.class);
    private ProtocolResqMsgProto.ProtocolRespMsg resp;
    private String[] chains ;
    public ClientTask(){

    }

    public void initThread() {
        ParentThread pt = getThread();
        if (pt != null) {
            //设置服务调用链chains
            String[] chains = pt.getChains();
            for (int i = 0; i < this.resp.getChainCount(); i++) {
                chains[i] = this.resp.getChain(i);
            }
            //设置uuid
            pt.setUuid(this.resp.getUuid());
            pt.setVersion(this.resp.getVersion());
            pt.setName("serverThreadPool-" + this.resp.getUuid());
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public void setResp(ProtocolResqMsgProto.ProtocolRespMsg resp) {
        this.resp = resp;
    }

    public ParentThread getThread() {
        return super.getThread();
    }
}
