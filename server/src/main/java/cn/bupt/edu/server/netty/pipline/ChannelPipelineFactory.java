package cn.bupt.edu.server.netty.pipline;

import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.server.netty.handler.DecodeHandler;
import cn.bupt.edu.server.netty.handler.EncodeHandler;
import cn.bupt.edu.server.netty.handler.HeartBeatHandler;
import cn.bupt.edu.server.netty.handler.ServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ChannelPipelineFactory extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
        ByteBuf delimiter = Unpooled.copiedBuffer(Const.DELIMITER);
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
        pipeline.addLast(new DecodeHandler());
        pipeline.addLast(new EncodeHandler());
        pipeline.addLast(new HeartBeatHandler());
        pipeline.addLast(new ServerHandler());
    }
}
