package cn.bupt.edu.task;

import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.task.device.DeviceTask;
import io.netty.channel.ChannelHandlerContext;

public class TaskFactory {
    public static ParentTask GetTask(ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        return new DeviceTask(req, ctx);
    }
}
