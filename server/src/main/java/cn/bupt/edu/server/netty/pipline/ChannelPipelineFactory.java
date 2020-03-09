package cn.bupt.edu.server.netty.pipline;

import cn.bupt.edu.server.netty.handler.DecodeHandler;
import cn.bupt.edu.server.netty.handler.EncodeHandler;
import cn.bupt.edu.server.netty.handler.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ChannelPipelineFactory extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // pipeline.addLast(new IdleStateHandler(2, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast(new DecodeHandler());
        pipeline.addLast(new EncodeHandler());
        pipeline.addLast(new ServerHandler());
        //pipeline.addLast(new HeartBeatHandler(0));
    }
}
