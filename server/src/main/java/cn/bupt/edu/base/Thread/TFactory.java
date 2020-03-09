package cn.bupt.edu.base.Thread;

import cn.bupt.edu.base.thread.ServerThread;

import java.util.concurrent.ThreadFactory;

public class TFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new ServerThread(r);
    }
}

