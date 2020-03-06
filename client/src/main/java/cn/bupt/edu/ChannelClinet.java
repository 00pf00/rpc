package cn.bupt.edu;

import cn.bupt.edu.pipline.ChannelPipelineFactory;
import cn.bupt.edu.protocol.ProtocolReqMsgProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

public class ChannelClinet {
    static final int PORT = 8100;
    static final String HOST = "localhost";

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        //创建Reactor处理线程池
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        //创建Pipeline的工厂类注册Handler处理网络I/O数据
        bootstrap.handler(new ChannelPipelineFactory());

        try {
            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
            Thread.sleep(3000);
//            ByteBuf buf = Unpooled.buffer(1);
//            buf.writeBytes(new byte[]{'1'});
//            future.channel().writeAndFlush(buf);
            ProtocolReqMsgProto.ProtocolReqMsg.Builder builder = ProtocolReqMsgProto.ProtocolReqMsg.newBuilder();
            builder.setUuid(UUID.randomUUID().toString());
            ProtocolReqMsgProto.ProtocolReqMsg req = builder.build();
            future.channel().writeAndFlush(req);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ChannelFuture getChannelFuture() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        //创建Reactor处理线程池
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        //创建Pipeline的工厂类注册Handler处理网络I/O数据
        bootstrap.handler(new ChannelPipelineFactory());
        ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
        return future;
    }
}
