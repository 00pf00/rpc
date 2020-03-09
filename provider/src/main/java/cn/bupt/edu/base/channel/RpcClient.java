package cn.bupt.edu.base.channel;

import cn.bupt.edu.base.ChannelClinet;
import cn.bupt.edu.base.threadpool.ClientThreadPool;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public class RpcClient {
    private static Channel ch;

    public static void Start() {
        try {
            ClientThreadPool.initThreadPool();
            ChannelFuture future = ChannelClinet.getInstance().getChannelFuture(7000);
            ch = future.channel();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Channel getChannel() {
        return ch;
    }
}
