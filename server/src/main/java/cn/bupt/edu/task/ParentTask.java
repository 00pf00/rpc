package cn.bupt.edu.task;

import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.thread.ParentThread;
import io.netty.channel.ChannelHandlerContext;

public abstract class ParentTask implements Runnable {
    public ProtocolReqMsgProto.ProtocolReqMsg Req;
    public ChannelHandlerContext Ctx;
    public ParentTask(ProtocolReqMsgProto.ProtocolReqMsg r,ChannelHandlerContext c){
        this.Ctx=c;
        this.Req = r;
    }
    public void initThread (){
        ParentThread p = getThread();
        //设置服务调用链chains
        String[] chains = p.getChains();
        for (int i = 0 ; i < this.Req.getChainCount();i++){
            chains[i] = this.Req.getChain(i);
        }
        //设置uuid
        p.setUuid(this.Req.getUuid());
        p.setVersion(this.Req.getVersion());
    }
    public ParentThread getThread(){
        Thread t = Thread.currentThread();
        ParentThread p = (ParentThread) t;
        return p;
    }
}
