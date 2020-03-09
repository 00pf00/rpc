package cn.bupt.edu.netty.handler;


import cn.bupt.edu.Thread.ServerThreadPool;
import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.task.TaskFactory;
import cn.bupt.edu.task.server.ServerFutureTask;
import cn.bupt.edu.task.server.ServerTask;
import cn.bupt.edu.util.Status;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private FutureTask<Void>[] flist = new FutureTask[100];
    private final static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        if (msg instanceof ProtocolReqMsgProto.ProtocolReqMsg) {
            ProtocolReqMsgProto.ProtocolReqMsg req = (ProtocolReqMsgProto.ProtocolReqMsg) msg;
            ExecutorService es = ServerThreadPool.getExecutorService(req.getPath());
            if (es == null){
                logger.error("The server-side processing request thread pool is not initialized path = {}",req.getPath());
                //将错误返回到client端
                ProtocolResqMsgProto.ProtocolRespMsg.Builder builder = ProtocolResqMsgProto.ProtocolRespMsg.newBuilder();
                builder.setUuid(req.getUuid());
                builder.setVersion(req.getVersion());
                builder.addAllChain(req.getChainList());
                builder.setStatus(Status.STATUS_NOTINIT);
                ctx.writeAndFlush(builder.build());
            }else {
                ServerTask task = TaskFactory.GetTask(req, ctx);
                ServerFutureTask ftask = new ServerFutureTask(task, null);
                es.execute(ftask);
                for (int i = 0; i < flist.length; i++) {
                    if (flist[i] == null || flist[i].isDone()) {
                        flist[i] = ftask;
                    }
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
