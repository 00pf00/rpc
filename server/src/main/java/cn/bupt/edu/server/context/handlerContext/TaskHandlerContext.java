package cn.bupt.edu.server.context.handlerContext;

import cn.bupt.edu.server.anotate.HandlerMapping;
import cn.bupt.edu.server.anotate.TaskMapping;
import cn.bupt.edu.server.context.HandlerContext;
import cn.bupt.edu.server.context.HandlerMethod;
import cn.bupt.edu.server.context.TaskContext;
import cn.bupt.edu.server.context.springContext.SpringContext;
import cn.bupt.edu.server.controller.HandlerController;
import cn.bupt.edu.server.task.DefaultTaskServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TaskHandlerContext implements HandlerContext, TaskContext {
    private final static Logger logger = LoggerFactory.getLogger(TaskHandlerContext.class);
    private static TaskHandlerContext ctx;
    private static ConcurrentHashMap<String, ArrayBlockingQueue<Object>> handlers = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ArrayBlockingQueue<Object>> tasks = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Method>> handlerMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, DefaultTaskServer> taskMap = new ConcurrentHashMap<>();

    public static TaskHandlerContext getInstance() {
        if (ctx == null) {
            ctx = new TaskHandlerContext();
        }
        return ctx;
    }

    @Override
    public void RegisterHandler(String path, HandlerController handler) {
        ConcurrentHashMap<String, Method> controller = new ConcurrentHashMap<>();
        java.lang.reflect.Method[] ms = handler.getClass().getMethods();
        for (int i = 0; i < ms.length; i++) {
            HandlerMapping handlerMapping = ms[i].getAnnotation(HandlerMapping.class);
            if (handlerMapping == null) {
                continue;
            }
            String values = handlerMapping.path();
            String mp = values.substring(1);
            controller.put(mp, ms[i]);
        }
        handlerMap.put(path, controller);
    }

    @Override
    public void RemoveHandler(String path) {

    }

    @Override
    public HandlerMethod GetHandler(String path) {
        String[] vs = path.split("/");
//        Object bean = SpringContext.getBean(vs[1]);
        ArrayBlockingQueue beanQueue = handlers.get(vs[1]);
        if (beanQueue == null) {
            logger.error("Service is not registered service = {} ", vs[1]);
            return null;
        }
        Object bean = null;
        try {
            bean = beanQueue.take();
        } catch (InterruptedException e) {
            logger.error("Failed to get service service = {} err = {}", vs[1], e.getMessage());
            return null;
        }
        ConcurrentHashMap<String, Method> service = handlerMap.get(vs[1]);
        if (service == null) {
            logger.error("Service is not registered service = {} ", vs[1]);
            return null;
        }
        Method m = service.get(vs[2]);
        if (m == null) {
            logger.error("Get method failed service = {} method = {}", vs[1], vs[2]);
        }
        HandlerMethod hm = new HandlerMethod(bean, m, vs[1]);
        return hm;
    }

    @Override
    public void SetHandler(HandlerMethod hm) {
        ArrayBlockingQueue<Object> beanQueue = handlers.get(hm.serviceName);
        if (beanQueue == null) {
            logger.error("Service is not registered service = {} ", hm.serviceName);
            return;
        }
        beanQueue.add(hm.object);
    }


    public void initContext(int... bc) {
        Map<String, HandlerController> handlers = SpringContext.getBeansOfType(HandlerController.class);
        for (Map.Entry<String, HandlerController> entry : handlers.entrySet()) {
            ctx.RegisterHandler(entry.getKey(), entry.getValue());
            if (bc.length > 0) {
                ArrayBlockingQueue<Object> beanQueue = new ArrayBlockingQueue<>(bc[1]);
                for (int i = 1; i < bc.length; i++) {
                    beanQueue.add(SpringContext.getBean(entry.getKey()));
                }
                this.handlers.put(entry.getKey(), beanQueue);
            } else {
                ArrayBlockingQueue<Object> beanQueue = new ArrayBlockingQueue<>(1);
                beanQueue.add(SpringContext.getBean(entry.getKey()));
                this.handlers.put(entry.getKey(), beanQueue);
            }
        }

        Map<String, DefaultTaskServer> tasks = SpringContext.getBeansOfType(DefaultTaskServer.class);
        for (Map.Entry<String, DefaultTaskServer> entry : tasks.entrySet()) {
            ctx.RegisterTask(entry.getValue());
            if (bc.length > 0) {
                ArrayBlockingQueue<Object> taskQueue = new ArrayBlockingQueue<>(bc[1]);
                for (int i = 1; i < bc.length; i++) {
                    taskQueue.add(SpringContext.getBean(entry.getKey()));
                }
                this.tasks.put(entry.getKey(), taskQueue);
            } else {
                ArrayBlockingQueue<Object> taskQueue = new ArrayBlockingQueue<>(1);
                taskQueue.add(SpringContext.getBean(entry.getKey()));
                this.tasks.put(entry.getKey(), taskQueue);
            }
        }

    }

    @Override
    public void RegisterTask(DefaultTaskServer task) {
        TaskMapping taskMapping = task.getClass().getAnnotation(TaskMapping.class);
        for (String path : taskMapping.paths()) {
            taskMap.put(path, task);
        }

    }

    @Override
    public DefaultTaskServer GetTask(String path) {
        return taskMap.get(path);
    }
}
