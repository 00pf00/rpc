package cn.bupt.edu.task.server;

import cn.bupt.edu.task.ParentTask;

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
}
