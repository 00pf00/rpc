package cn.bupt.edu.server.netty.pipline;

import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.server.netty.handler.DecodeHandler;
import cn.bupt.edu.server.netty.handler.EncodeHandler;
import cn.bupt.edu.server.netty.handler.ServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class ChannelPipelineFactory extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // pipeline.addLast(new IdleStateHandler(2, 0, 0, TimeUnit.SECONDS));
        ByteBuf delimiter = Unpooled.copiedBuffer(Const.DELIMITER);
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
        pipeline.addLast(new DecodeHandler());
        pipeline.addLast(new EncodeHandler());
        pipeline.addLast(new ServerHandler());
        //pipeline.addLast(new HeartBeatHandler(0));
    }
}
