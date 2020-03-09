package cn.bupt.edu.base.task.server;

import cn.bupt.edu.base.task.ParentTask;

import java.util.concurrent.FutureTask;

public class ServerFutureTask extends FutureTask<Void> {
    private Runnable serverTask;

    public ServerFutureTask(Runnable runnable, Void result) {
        super(runnable, result);
        serverTask = runnable;
    }

    public ParentTask getParentTask() {
        if (serverTask instanceof ParentTask) {
            return (ParentTask) serverTask;
        }
        return null;
    }

    public ServerTask getServerTask() {
        if (serverTask instanceof ServerTask) {
            return (ServerTask) serverTask;
        }
        return null;
    }
}
