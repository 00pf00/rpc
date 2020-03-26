package cn.bupt.edu.base.task.server;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.task.AbstractParentTask;
import cn.bupt.edu.base.task.ParentTask;
import cn.bupt.edu.base.thread.ParentThread;
import cn.bupt.edu.base.util.LogInfo;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServerTask extends AbstractParentTask implements Runnable, ParentTask {
    private final static Logger logger = LoggerFactory.getLogger(ServerTask.class);
    public ProtocolReqMsgProto.ProtocolReqMsg Req;
    public ChannelHandlerContext Ctx;

    public ServerTask(ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        this.Req = req;
        this.Ctx = ctx;
    }

    public void initThread() {
        ParentThread pt = getThread();
        //设置服务调用链chains
        String[] chains = pt.getChains();
        for (int i = 0; i < this.Req.getChainCount(); i++) {
            chains[i] = this.Req.getChain(i);
        }
        //设置uuid
        pt.setUuid(this.Req.getUuid());
        pt.setVersion(this.Req.getVersion());
        pt.setName(LogInfo.SERVER_THREADPOOL + this.Req.getUuid());
    }

    public Logger getLogger() {
        return logger;
    }

    public ParentThread getThread() {
        return super.getThread();
    }


}
