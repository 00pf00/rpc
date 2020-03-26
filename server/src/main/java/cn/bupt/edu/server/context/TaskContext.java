package cn.bupt.edu.server.context;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.server.task.DefaultServerTask;
import io.netty.channel.ChannelHandlerContext;

public interface TaskContext {
    public void RegisterTask(DefaultServerTask task);

    public DefaultServerTask GetTask(String path, ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx);
}
