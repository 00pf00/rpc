package cn.bupt.edu.channel;

import cn.bupt.edu.ChannelClinet;
import cn.bupt.edu.threadpool.ClientThreadPool;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public class RpcClient {
    private static Channel ch;

    public static void Start() {
        try {
            ClientThreadPool.initThreadPool();
            ChannelFuture future = ChannelClinet.getChannelFuture();
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
