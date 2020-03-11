package cn.bupt.edu.consumer.concurrency;

import java.util.concurrent.CountDownLatch;

public abstract class IPRunnable implements Runnable {
    public CountDownLatch CountDownLatch;

    public IPRunnable(CountDownLatch c) {
        CountDownLatch = c;
    }
}
