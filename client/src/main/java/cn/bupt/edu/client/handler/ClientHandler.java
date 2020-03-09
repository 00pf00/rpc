package cn.bupt.edu.client.handler;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.task.client.ClientFutureTask;
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
}
