package cn.bupt.edu.blockqueue;

import cn.bupt.edu.Task.ParentFutureTask;

import java.util.concurrent.ArrayBlockingQueue;

public class ClientBlockQueue {

    public static ArrayBlockingQueue<Runnable> clientTask = new ArrayBlockingQueue<>(10);


}
