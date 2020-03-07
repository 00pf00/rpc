package cn.bupt.edu.datadispatch;

import cn.bupt.edu.Task.ClientParentFutureTask;

import java.util.concurrent.ConcurrentHashMap;

public class ClientTask {
    private static ClientTask task;
    private ConcurrentHashMap<String, ClientParentFutureTask> taskMap;

    public static ClientTask getInstance() {
        if (task == null) {
            task = new ClientTask();
            task.taskMap = new ConcurrentHashMap<String, ClientParentFutureTask>();
        }
        return task;
    }

    public ClientParentFutureTask getTask(String uuid) {
        return taskMap.get(uuid);
    }

    public void putTask(String uuid, ClientParentFutureTask task) {
        taskMap.put(uuid, task);
    }

}
