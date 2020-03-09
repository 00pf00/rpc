package cn.bupt.edu.base.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

public class ClientBlockQueue {

    public static ArrayBlockingQueue<Runnable> clientTask = new ArrayBlockingQueue<>(10);


}
