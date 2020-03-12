package cn.bupt.edu.consumer.channel;

import cn.bupt.edu.client.clientmanagement.ClientManagement;
import cn.bupt.edu.client.clientmanagement.RpcClient;
import cn.bupt.edu.client.threadpool.ClientThreadPool;
import io.netty.channel.Channel;

public class Client {
    private static RpcClient client;

    public static void Start() {
        ClientThreadPool.initThreadPool();
        client = ClientManagement.RegisterChannelClient("127.0.0.1", 8100);
    }

    public static Channel getChannel() {
        return client.getChannel();
    }
}
