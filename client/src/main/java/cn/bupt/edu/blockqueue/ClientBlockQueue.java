package cn.bupt.edu.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

public class ClientBlockQueue {

    public static ArrayBlockingQueue<Runnable> clientTask = new ArrayBlockingQueue<>(10);


}
