package cn.bupt.edu.server.task;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.task.server.ServerTask;
import cn.bupt.edu.server.task.device.DeviceTaskServer;
import io.netty.channel.ChannelHandlerContext;

public class TaskFactory {
    public static ServerTask GetTask(ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        return new DeviceTaskServer(req, ctx);
    }
}
