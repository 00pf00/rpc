package cn.bupt.edu.base.task;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.task.device.DeviceTaskServer;
import cn.bupt.edu.base.task.server.ServerTask;
import io.netty.channel.ChannelHandlerContext;

public class TaskFactory {
    public static ServerTask GetTask(ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        return new DeviceTaskServer(req, ctx);
    }
}
