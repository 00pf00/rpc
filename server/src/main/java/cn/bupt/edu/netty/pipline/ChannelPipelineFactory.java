package cn.bupt.edu.netty.pipline;

import cn.bupt.edu.netty.handler.DecodeHandler;
import cn.bupt.edu.netty.handler.EncodeHandler;
import cn.bupt.edu.netty.handler.HeartBeatHandler;
import cn.bupt.edu.netty.handler.Serverhandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ChannelPipelineFactory extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // pipeline.addLast(new IdleStateHandler(2, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast(new DecodeHandler());
        pipeline.addLast(new EncodeHandler());
        pipeline.addLast(new Serverhandler());
        //pipeline.addLast(new HeartBeatHandler(0));
    }
}
