package cn.bupt.edu.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.FutureTask;

public class BlockQueue {
    public static ArrayBlockingQueue<Runnable> tasks = new ArrayBlockingQueue<Runnable>(10);

    public static void Add(FutureTask<Void> task) {
        tasks.add(task);
    }

    public static Runnable Take() throws InterruptedException {
        return tasks.take();
    }


}
