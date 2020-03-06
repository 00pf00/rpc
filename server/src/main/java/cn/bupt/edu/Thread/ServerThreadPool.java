package cn.bupt.edu.Thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static cn.bupt.edu.blockqueue.BlockQueue.tasks;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ServerThreadPool {
    private static ExecutorService task;

    public static void initThreadPool() {
        task = new ThreadPoolExecutor(2,
                4,
                10,
                SECONDS,
                tasks,
                new TFactory());
        for (int i = 0; i < 2; i++) {
            task.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程池启动\n");
                }
            });
        }
    }
}
