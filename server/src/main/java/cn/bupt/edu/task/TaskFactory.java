package cn.bupt.edu.task;

import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.task.device.DeviceTaskServer;
import cn.bupt.edu.task.server.ServerTask;
import io.netty.channel.ChannelHandlerContext;

public class TaskFactory {
    public static ServerTask GetTask(ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        return new DeviceTaskServer(req, ctx);
    }
}
