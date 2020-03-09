package cn.bupt.edu.base.threadfactory;

import cn.bupt.edu.base.thread.ServerThread;

import java.util.concurrent.ThreadFactory;

public class ServerTFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new ServerThread(r);
    }
}
