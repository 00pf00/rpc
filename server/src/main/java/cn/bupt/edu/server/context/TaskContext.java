package cn.bupt.edu.server.context;

import cn.bupt.edu.server.task.DefaultTaskServer;

import java.util.concurrent.ArrayBlockingQueue;

public interface TaskContext {
    public void RegisterTask(DefaultTaskServer task, ArrayBlockingQueue<DefaultTaskServer> queue);

    public DefaultTaskServer GetTask(String path);
}
