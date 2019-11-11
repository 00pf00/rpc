package cn.bupt.edu.netty.consumer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueConsumer implements Runnable{
    public static LinkedBlockingQueue<Channel> channels = new LinkedBlockingQueue<Channel>();

    public static void Consumer (){
        while (true){
            Channel ch = null;
            try {
                ch = channels.take();
                ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.directBuffer(10,100);
                buf.writeBytes(new byte[]{'a','b','c'});
                ch.writeAndFlush(buf);
                ch.closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void run() {
        Consumer();
    }
}
