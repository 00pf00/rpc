package cn.bupt.edu.pipline;

import cn.bupt.edu.handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class ChannelPipelineFactory extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipline = socketChannel.pipeline();
        //pipline.addLast(new IdleStateHandler(2,2,2));
        //pipline.addLast(new HeartBeatHandler(10));
        pipline.addLast(new EncodeHandler());
        pipline.addLast(new DecodeHandler());
        pipline.addLast(new ClientHandler());

    }
}
