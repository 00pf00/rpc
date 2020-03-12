package cn.bupt.edu.client.datadispatch;

import cn.bupt.edu.base.protocol.ProtocolResqMsgProto;
import cn.bupt.edu.base.task.client.ClientFutureTask;
import cn.bupt.edu.client.threadpool.ClientThreadPool;

import java.util.Map;
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

    public void removeAllTask(ProtocolResqMsgProto.ProtocolRespMsg resp) {
        for (Map.Entry<String, ClientFutureTask> entry : taskMap.entrySet()) {
            entry.getValue().setResp(resp);
            ClientThreadPool.getExecutorService().execute(entry.getValue());
            taskMap.remove(entry.getKey());
        }
    }
}
