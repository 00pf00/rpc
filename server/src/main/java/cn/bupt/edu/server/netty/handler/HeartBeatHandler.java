package cn.bupt.edu.server.netty.handler;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.base.util.Status;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    private ProtocolResqMsgProto.ProtocolRespMsg resp;

    public HeartBeatHandler() {
        ProtocolResqMsgProto.ProtocolRespMsg.Builder builder = ProtocolResqMsgProto.ProtocolRespMsg.newBuilder();
        builder.setStatus(Status.STATUS_HEARTBEAT);
        resp = builder.build();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        if (msg instanceof ProtocolReqMsgProto.ProtocolReqMsg) {
            ProtocolReqMsgProto.ProtocolReqMsg req = (ProtocolReqMsgProto.ProtocolReqMsg) msg;
            if (req.getPath().equals(Const.REQ_HEARTBEAT)) {
                ctx.writeAndFlush(resp);
            } else {
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }

    }
}
