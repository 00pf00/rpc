package cn.bupt.edu.threadpool;

import cn.bupt.edu.blockqueue.ClientBlockQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClientThreadPool {
    private static ExecutorService clientThreadPool;
    public static void initThreadPool(){
        clientThreadPool =  new ThreadPoolExecutor(2,4,10, TimeUnit.SECONDS, ClientBlockQueue.clientTask);
        for (int i = 0 ; i < 2 ; i++){
            clientThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("客户端线程池启动！\n");
                }
            });
        }
    }
}
