package cn.bupt.edu.server;


import cn.bupt.edu.server.context.handlerContext.TaskContext;
import cn.bupt.edu.server.netty.pipline.ChannelPipelineFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


//@SpringBootApplication
public class ChannelServer {

    public static int PORT = 8100;
    private static ChannelServer channelServer;

    public static void main(String[] args) {
//        SpringApplication.run(ChannelServer.class, args);
//        TaskContext.getInstance().initContext();
//        ServerThreadPool.initThreadPool();
//        NettyServer();
    }

    public static void NettyServer() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        //处理连接建立的Acceptor线程池
        EventLoopGroup acceptor = new NioEventLoopGroup();
        //处理网络I/O读写的线程池
        EventLoopGroup handler = new NioEventLoopGroup();
        bootstrap.group(acceptor, handler);
        bootstrap.channel(NioServerSocketChannel.class);
        //创建Pipeline的工厂类注册Handler处理网络I/O数据
        bootstrap.childHandler(new ChannelPipelineFactory());
        bootstrap.option(ChannelOption.SO_BACKLOG, 100);
        bootstrap.handler(new LoggingHandler(LogLevel.INFO));
        try {
            ChannelFuture future = bootstrap.bind(PORT).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ChannelServer getInstance() {
        if (channelServer == null) {
            channelServer = new ChannelServer();
        }
        return channelServer;
    }

    public void initChannelServer(int... port) {
        if (port.length > 0) {
            this.PORT = port[0];
        }
        TaskContext.getInstance().initContext();
        NettyServer();
    }
}
