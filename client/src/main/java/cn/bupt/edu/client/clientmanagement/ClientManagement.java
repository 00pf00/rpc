package cn.bupt.edu.client.clientmanagement;

import cn.bupt.edu.client.pipline.ChannelPipelineFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ClientManagement {
    private final static Logger logger = LoggerFactory.getLogger(ClientManagement.class);
    private static ConcurrentHashMap<String, RpcClient> clientManagement = new ConcurrentHashMap<>();

    public static RpcClient RegisterChannelClient(String ip, int port) {
        Channel ch = getChannel(ip, port);
        RpcClient rc = new RpcClient(ch);
        clientManagement.put(ip + ":" + port, rc);
        return rc;

    }

    public static Channel getChannel(String ip, int port) {
        Bootstrap bootstrap = new Bootstrap();
        //创建Reactor处理线程池
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        //创建Pipeline的工厂类注册Handler处理网络I/O数据
        bootstrap.handler(new ChannelPipelineFactory());

        ChannelFuture future = null;
        while (future == null) {
            try {
                future = bootstrap.connect(ip, port).sync();
            } catch (Exception e) {
                logger.error("Client failed to connect to Server");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return future.channel();
    }

    public static RpcClient getRpcClient(String ip, String port) {
        return clientManagement.get(ip + ":" + port);
    }
}
