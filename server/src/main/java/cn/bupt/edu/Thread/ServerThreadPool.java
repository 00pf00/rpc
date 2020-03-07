package cn.bupt.edu.Thread;

import cn.bupt.edu.threadpool.ServerThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

import static cn.bupt.edu.blockqueue.BlockQueue.tasks;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ServerThreadPool {
    private final static Logger logger = LoggerFactory.getLogger(ServerThreadPool.class);
    private static ExecutorService task;

    public static void initThreadPool() {
        task = new ServerThreadPoolExecutor(2,
                4,
                10,
                SECONDS,
                tasks,
                new TFactory());
        task.submit(new Runnable() {
            @Override
            public void run() {
                logger.info("ServerThreadPool start !");
            }
        });
    }
}
