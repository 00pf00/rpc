package cn.bupt.edu.threadpool;

import cn.bupt.edu.blockqueue.ClientBlockQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClientThreadPool {
    private final static Logger logger = LoggerFactory.getLogger(ClientThreadPool.class);
    private static ExecutorService clientThreadPool;

    public static void initThreadPool() {
        clientThreadPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, ClientBlockQueue.clientTask);
        clientThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                logger.info("clientThreadPool start!");
            }
        });
    }
}
