package cn.bupt.edu.base.threadpool;

import cn.bupt.edu.base.task.ParentTask;
import cn.bupt.edu.base.task.server.ServerFutureTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ServerThreadPoolExecutor extends ParentThreadPoolExecutor {
    public ServerThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public ServerThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    //执行task任务之前初始化线程
    protected void beforeExecute(Thread t, Runnable r) {
        if (r instanceof ServerFutureTask) {
            ServerFutureTask spf = (ServerFutureTask) r;
            ParentTask srt = spf.getParentTask();
            if (srt != null) {
                srt.initThread();
            }
        }
    }

}
