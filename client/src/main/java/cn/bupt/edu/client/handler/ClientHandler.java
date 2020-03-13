package cn.bupt.edu.client.handler;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.task.client.ClientFutureTask;
import cn.bupt.edu.base.util.Status;
import cn.bupt.edu.client.datadispatch.ClientTaskMap;
import cn.bupt.edu.client.threadpool.ClientThreadPool;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        if (msg instanceof ProtocolResqMsgProto.ProtocolRespMsg) {
            ProtocolResqMsgProto.ProtocolRespMsg resp = (ProtocolResqMsgProto.ProtocolRespMsg) msg;
            ClientFutureTask task = ClientTaskMap.getInstance().getTask(resp.getUuid());
            ClientTaskMap.getInstance().removeTask(resp.getUuid());
            if (task != null) {
                task.setResp(resp);
                ClientThreadPool.getExecutorService().execute(task);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ProtocolResqMsgProto.ProtocolRespMsg.Builder builder = ProtocolResqMsgProto.ProtocolRespMsg.newBuilder();
        builder.setStatus(Status.STATUS_DISCONNECT);
        ProtocolResqMsgProto.ProtocolRespMsg resp = builder.build();
        ClientTaskMap.getInstance().removeAllTask(resp);
        ctx.handler().handlerRemoved(ctx);
        ctx.close();
    }
}
