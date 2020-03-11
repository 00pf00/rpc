package cn.bupt.edu.client.pipline;

import cn.bupt.edu.base.util.Const;
import cn.bupt.edu.client.handler.ClientHandler;
import cn.bupt.edu.client.handler.DecodeHandler;
import cn.bupt.edu.client.handler.EncodeHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class ChannelPipelineFactory extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //pipline.addLast(new IdleStateHandler(2,2,2));
        //pipline.addLast(new HeartBeatHandler(10));
        ByteBuf delimiter = Unpooled.copiedBuffer(Const.DELIMITER);
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
        pipeline.addLast(new EncodeHandler());
        pipeline.addLast(new DecodeHandler());
        pipeline.addLast(new ClientHandler());

    }
}
