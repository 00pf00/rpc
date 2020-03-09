package cn.bupt.edu.rejecthandler;


import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.task.server.ServerFutureTask;
import cn.bupt.edu.task.server.ServerTask;
import cn.bupt.edu.util.Status;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (r instanceof ServerFutureTask){
            ServerFutureTask sft = (ServerFutureTask)r;
            ServerTask st = sft.getServerTask();
            ProtocolResqMsgProto.ProtocolRespMsg.Builder builder = ProtocolResqMsgProto.ProtocolRespMsg.newBuilder();
            builder.setUuid(st.Req.getUuid());
            builder.setVersion(st.Req.getVersion());
            builder.setStatus(Status.STATUS_REJECT);
            builder.addAllChain(st.Req.getChainList());
            ProtocolResqMsgProto.ProtocolRespMsg resp = builder.build();
            st.Ctx.writeAndFlush(resp);
        }
    }
}
