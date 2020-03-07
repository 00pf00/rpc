package cn.bupt.edu.netty.handler;


import cn.bupt.edu.blockqueue.BlockQueue;
import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.task.TaskFactory;
import cn.bupt.edu.task.server.ServerFutureTask;
import cn.bupt.edu.task.server.ServerTask;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.FutureTask;

public class Serverhandler extends ChannelInboundHandlerAdapter {
    private FutureTask<Void>[] flist = new FutureTask[100];

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        if (msg instanceof ProtocolReqMsgProto.ProtocolReqMsg) {
            ServerTask task = TaskFactory.GetTask((ProtocolReqMsgProto.ProtocolReqMsg) msg, ctx);
            ServerFutureTask ftask = new ServerFutureTask(task, null);
            BlockQueue.Add(ftask);
            for (int i = 0; i < flist.length; i++) {
                if (flist[i] == null || flist[i].isDone()) {
                    flist[i] = ftask;
                }
            }
        } else {
            ctx.fireChannelRead(msg);
        }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            for (FutureTask<Void> f : this.flist) {
                if (!f.isDone()) {
                    f.cancel(true);
                }
            }
        }
        ctx.close();
    }

}
