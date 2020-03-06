package cn.bupt.edu.datadispatch;

import cn.bupt.edu.Task.ParentFutureTask;

import java.util.concurrent.ConcurrentHashMap;

public class ClientTask {
    private static ClientTask task;
    private ConcurrentHashMap<String, ParentFutureTask> taskMap;

    public static ClientTask getInstance() {
        if (task == null) {
            task = new ClientTask();
            task.taskMap = new ConcurrentHashMap<String, ParentFutureTask>();
        }
        return task;
    }

    public ParentFutureTask getTask(String uuid) {
        return taskMap.get(uuid);
    }

    public void putTask(String uuid, ParentFutureTask task) {
        taskMap.put(uuid, task);
    }

}
