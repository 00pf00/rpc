package cn.bupt.edu.base.handler;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class DispatchHandler extends ChannelInboundHandlerAdapter {
    private ArrayBlockingQueue<Map<String, byte[]>> req; //请求队列
    private ConcurrentHashMap<String, byte[]> resp; //
    private ConcurrentHashMap<String, Runnable> wtask;
    private ArrayBlockingQueue<Runnable> task;

    public DispatchHandler(
            ArrayBlockingQueue<Map<String, byte[]>> creq,
            ConcurrentHashMap<String, byte[]> cresp,
            ConcurrentHashMap<String, Runnable> cwtask,
            ArrayBlockingQueue<Runnable> ctask) {
        this.req = creq;
        this.resp = cresp;
        this.wtask = cwtask;
        this.task = ctask;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ProtocolResqMsgProto.ProtocolRespMsg) {
            ProtocolResqMsgProto.ProtocolRespMsg resp = (ProtocolResqMsgProto.ProtocolRespMsg) msg;
            int length = resp.getBody().size();
            byte[] rb = new byte[length];
            resp.getBody().copyTo(rb, 0);
            this.resp.put(resp.getUuid(), rb);

        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
