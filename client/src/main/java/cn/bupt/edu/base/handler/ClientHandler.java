package cn.bupt.edu.base.handler;

import cn.bupt.edu.base.Task.ClientParentFutureTask;
import cn.bupt.edu.base.blockqueue.ClientBlockQueue;
import cn.bupt.edu.base.datadispatch.ClientResp;
import cn.bupt.edu.base.datadispatch.ClientTask;
import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        if (msg instanceof ProtocolResqMsgProto.ProtocolRespMsg) {
            ProtocolResqMsgProto.ProtocolRespMsg resp = (ProtocolResqMsgProto.ProtocolRespMsg) msg;
            ClientParentFutureTask task = ClientTask.getInstance().getTask(resp.getUuid());
            if (task != null) {
                ClientResp.getInstance().addResp(resp.getUuid(), resp);
                ClientBlockQueue.clientTask.add(task);
            }
        } else {
            ctx.fireChannelRead(msg);
        }

    }
}