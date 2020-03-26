package cn.bupt.edu.server.context;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.server.task.DefaultTaskServer;
import io.netty.channel.ChannelHandlerContext;

public interface TaskContext {
    public void RegisterTask(DefaultTaskServer task);

    public DefaultTaskServer GetTask(String path, ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx);
}
