package cn.bupt.edu.task.device;

import cn.bupt.edu.context.HandlerMethod;
import cn.bupt.edu.context.handlerContext.TaskContext;
import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.task.server.ServerTask;
import cn.bupt.edu.thread.ParentThread;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;

public class DeviceTaskServer extends ServerTask {
    public DeviceTaskServer(ProtocolReqMsgProto.ProtocolReqMsg r, ChannelHandlerContext c) {
        super(r, c);
    }

    @Override
    public void run() {
        ProtocolResqMsgProto.ProtocolRespMsg.Builder builder = ProtocolResqMsgProto.ProtocolRespMsg.newBuilder();
        HandlerMethod hm = TaskContext.getInstance().GetHandler(this.Req.getPath());
        try {
            //调用业务处理方法
            Object resp = hm.method.invoke(hm.object, this.Req.getBody());
            byte[] rb = (byte[]) resp;
            //将处理结果编码后的二进制对象放入ProtoRespMsg对象的body中
            builder.setBody(ByteString.copyFrom(rb));
            builder.setStatus(200);
        } catch (Exception e) {
            builder.setBody(ByteString.copyFrom(e.toString().getBytes()));
            builder.setStatus(500);
            e.printStackTrace();
        } finally {
            TaskContext.getInstance().SetHandler(hm);
            ParentThread p = super.getThread();
            String[] chains = p.getChains();
            for (int i = 0; i < chains.length; i++) {
                if (chains[i] != null) {
                    builder.addChain(chains[i]);
                }
            }
            builder.setUuid(p.getUuid());
            builder.setVersion(p.getVersion());
            ProtocolResqMsgProto.ProtocolRespMsg result = builder.build();
            this.Ctx.writeAndFlush(result);
        }
    }


}
