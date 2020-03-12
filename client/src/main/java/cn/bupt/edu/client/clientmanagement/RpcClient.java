package cn.bupt.edu.client.clientmanagement;

import io.netty.channel.Channel;

public class RpcClient {
    private Channel channel;

    public RpcClient(Channel ch) {
        this.channel = ch;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
