package cn.bupt.edu.Thread;

import cn.bupt.edu.thread.ServerThread;

import java.util.concurrent.ThreadFactory;

public class TFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new ServerThread(r);
    }
}

