package cn.bupt.edu.base.threadfactory;

import cn.bupt.edu.base.thread.ClientThread;

import java.util.concurrent.ThreadFactory;

public class ClientTFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new ClientThread(r);
    }
}
