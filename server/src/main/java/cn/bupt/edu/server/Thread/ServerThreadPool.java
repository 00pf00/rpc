package cn.bupt.edu.server.Thread;

import cn.bupt.edu.base.rejecthandler.ServerRejectedExecutionHandler;
import cn.bupt.edu.base.threadfactory.ServerTFactory;
import cn.bupt.edu.base.threadpool.ServerThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ServerThreadPool {
    private final static Logger logger = LoggerFactory.getLogger(ServerThreadPool.class);
    private static ConcurrentHashMap<String, ExecutorService> esMap = new ConcurrentHashMap<>();
    private static ExecutorService es = new ServerThreadPoolExecutor(2, 4, 10, SECONDS, new ArrayBlockingQueue<Runnable>(10), new ServerTFactory(), new ServerRejectedExecutionHandler());


    public static void initThreadPool(String... servcie) {
        if (servcie.length > 0) {
            for (int i = 0; i < servcie.length; i++) {
                ArrayBlockingQueue<Runnable> tasks = new ArrayBlockingQueue<Runnable>(5000);
                ExecutorService task = new ServerThreadPoolExecutor(2, 4, 10, SECONDS, tasks, new ServerTFactory(), new ServerRejectedExecutionHandler());
                esMap.put(servcie[i], task);
            }
        }

    }

    public static ExecutorService getExecutorService(String path) {
        String[] paths = path.split("/");
        if (paths.length > 1) {
            ExecutorService res = esMap.get(paths[1]);
            if (res != null) {
                return res;
            }
        }
        return es;
    }
}
