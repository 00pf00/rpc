package cn.bupt.edu.base.datadispatch;

import cn.bupt.edu.base.Task.ClientParentFutureTask;

import java.util.concurrent.ConcurrentHashMap;

public class ClientTask {
    private static ClientTask task = new ClientTask();
    private ConcurrentHashMap<String, ClientParentFutureTask> taskMap = new ConcurrentHashMap<>();

    public static ClientTask getInstance() {
        return task;
    }

    public ClientParentFutureTask getTask(String uuid) {
        return taskMap.get(uuid);
    }

    public void putTask(String uuid, ClientParentFutureTask task) {
        taskMap.put(uuid, task);
    }

}
