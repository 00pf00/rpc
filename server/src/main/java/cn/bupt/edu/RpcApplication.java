package cn.bupt.edu;


import cn.bupt.edu.Thread.ServerThreadPool;
import cn.bupt.edu.context.handlerContext.TaskContext;
import cn.bupt.edu.netty.handler.HeartBeatHandler;
import cn.bupt.edu.netty.pipline.ChannelPipelineFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RpcApplication {
    static final int PORT = 8100;
    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class, args);
        TaskContext.getInstance().initContext();
        ServerThreadPool.initThreadPool();
        NettyServer();
    }
    public static void NettyServer(){
        ServerBootstrap bootstrap = new ServerBootstrap();
        //处理连接建立的Acceptor线程池
        EventLoopGroup acceptor = new NioEventLoopGroup();
        //处理网络I/O读写的线程池
        EventLoopGroup handler = new NioEventLoopGroup();
        bootstrap.group(acceptor,handler);
        bootstrap.channel(NioServerSocketChannel.class);
        //创建Pipeline的工厂类注册Handler处理网络I/O数据
        bootstrap.childHandler(new ChannelPipelineFactory());
        bootstrap.option(ChannelOption.SO_BACKLOG,100);
        bootstrap.handler(new LoggingHandler(LogLevel.INFO));
        try {
            ChannelFuture future = bootstrap.bind(PORT).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
