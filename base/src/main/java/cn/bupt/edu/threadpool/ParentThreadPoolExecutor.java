package cn.bupt.edu.threadpool;

import cn.bupt.edu.task.ParentTask;
import cn.bupt.edu.task.server.ServerFutureTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParentThreadPoolExecutor extends ThreadPoolExecutor {
    public ParentThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
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
