package cn.bupt.edu.task.service;

import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import io.netty.channel.ChannelHandlerContext;
import cn.bupt.edu.task.ParentTask;

public class ServiceTask extends ParentTask {


    public ServiceTask(ProtocolReqMsgProto.ProtocolReqMsg r, ChannelHandlerContext c) {
        super(r, c);
    }

    @Override
    public void run() {

    }
}
