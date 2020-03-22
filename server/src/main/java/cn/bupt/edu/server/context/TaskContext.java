package cn.bupt.edu.server.context;

import cn.bupt.edu.server.task.DefaultTaskServer;

public interface TaskContext {
    public void RegisterTask(DefaultTaskServer task);

    public DefaultTaskServer GetTask(String path);
}
