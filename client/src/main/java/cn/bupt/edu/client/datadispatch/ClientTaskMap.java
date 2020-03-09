package cn.bupt.edu.client.datadispatch;

import cn.bupt.edu.base.task.client.ClientFutureTask;

import java.util.concurrent.ConcurrentHashMap;

public class ClientTaskMap {
    private static ClientTaskMap task = new ClientTaskMap();
    private ConcurrentHashMap<String, ClientFutureTask> taskMap = new ConcurrentHashMap<>();

    public static ClientTaskMap getInstance() {
        return task;
    }

    public ClientFutureTask getTask(String uuid) {
        return taskMap.get(uuid);
    }

    public void removeTask(String uuid) {
        taskMap.remove(uuid);
    }

    public void putTask(String uuid, ClientFutureTask task) {
        taskMap.put(uuid, task);
    }


}
