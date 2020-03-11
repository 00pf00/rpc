package cn.bupt.edu.server.task.device;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.task.server.ServerTask;
import cn.bupt.edu.base.thread.ParentThread;
import cn.bupt.edu.base.util.LogInfo;
import cn.bupt.edu.server.context.HandlerMethod;
import cn.bupt.edu.server.context.handlerContext.TaskContext;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;

public class DeviceTaskServer extends ServerTask {
    public DeviceTaskServer(ProtocolReqMsgProto.ProtocolReqMsg r, ChannelHandlerContext c) {
        super(r, c);
    }

    @Override
    public void run() {
        JSONObject task = new JSONObject();
        task.put(LogInfo.UUID, this.Req.getUuid());
        task.put(LogInfo.LOGO,LogInfo.SERVER_TASK_SATRT);
        this.getLogger().info(task.toJSONString());
        ProtocolResqMsgProto.ProtocolRespMsg.Builder builder = ProtocolResqMsgProto.ProtocolRespMsg.newBuilder();
        HandlerMethod hm = TaskContext.getInstance().GetHandler(this.Req.getPath());
        try {
            //调用业务处理方法
            task.put(LogInfo.SERVER_METHOD, hm.method.getName());
            task.put(LogInfo.LOGO, LogInfo.SERVER_PROCESSING_METHD_START);
            this.getLogger().info(task.toJSONString());
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
            task.put(LogInfo.LOGO, LogInfo.SERVER_PROCESSING_METHD_END);
            this.getLogger().info(task.toJSONString());

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
            task.remove(LogInfo.SERVER_METHOD);
            task.put(LogInfo.LOGO,LogInfo.SERVER_TASK_END);
            this.getLogger().info(task.toJSONString());
        }
    }


}
