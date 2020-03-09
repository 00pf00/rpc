package cn.bupt.edu.client.threadpool;

import cn.bupt.edu.base.threadfactory.ClientTFactory;
import cn.bupt.edu.base.threadpool.ClientThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientThreadPool {
    private final static Logger logger = LoggerFactory.getLogger(ClientThreadPool.class);
    private static ExecutorService clientThreadPool;

    public static void initThreadPool() {
        clientThreadPool = new ClientThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ClientTFactory());
        logger.info("clientThreadPool initialized successfully !");
    }

    public static ExecutorService getExecutorService() {
        return clientThreadPool;
    }
}
