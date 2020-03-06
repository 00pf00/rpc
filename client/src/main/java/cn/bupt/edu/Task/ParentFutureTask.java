package cn.bupt.edu.Task;

import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.thread.ParentThread;
import com.google.protobuf.ByteString;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ParentFutureTask extends FutureTask<Object> {
    private ProtocolResqMsgProto.ProtocolRespMsg resp;
    public ParentFutureTask(Callable<Object> callable) {
        super(callable);
    }
    public Object get() throws InterruptedException, ExecutionException {
        Object result = super.get();
        Thread thread = Thread.currentThread();
        if (thread instanceof ParentThread){
            ParentThread pt = (ParentThread)thread;
            String[] chains = pt.getChains();
            for (int i = 0 ; i < resp.getChainCount();i++){
                chains[i] = resp.getChain(i);
            }
        }
        return super.get();
    }
}
