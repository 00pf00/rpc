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
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TaskHandlerContext implements HandlerContext, TaskContext {
    private final static Logger logger = LoggerFactory.getLogger(TaskHandlerContext.class);
    private static TaskHandlerContext ctx;
    private static ConcurrentHashMap<String, ArrayBlockingQueue<Object>> handlers = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ArrayBlockingQueue<DefaultTaskServer>> tasks = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Method>> handlerMap = new ConcurrentHashMap<>();

    public static TaskHandlerContext getInstance() {
        if (ctx == null) {
            ctx = new TaskHandlerContext();
        }
        return ctx;
    }

    @Override
    public void RegisterMethod(String path, HandlerController handler, int... bc) {
        ConcurrentHashMap<String, Method> controller = new ConcurrentHashMap<>();
        java.lang.reflect.Method[] ms = handler.getClass().getMethods();
        boolean flag = false;
        for (int i = 0; i < ms.length; i++) {
            HandlerMapping handlerMapping = AnnotationUtils.findAnnotation(ms[i],HandlerMapping.class);
            if (handlerMapping == null) {
                continue;
            }
            logger.info("method name = {}",handlerMapping.path());
            controller.put(handlerMapping.path(), ms[i]);
            if (!flag) {
                flag = true;
            }
        }
        if (flag) {
            RequestMapping rm =AnnotationUtils.findAnnotation(handler.getClass(),RequestMapping.class);
            logger.info("name = {}",rm.name());
            //RequestMapping rm = handler.getClass().getAnnotation(RequestMapping.class);
            if (rm == null) {
                handlerMap.put("/" + path, controller);
                logger.info("register service = {}",path);
            } else {
                handlerMap.put(rm.name(), controller);
                logger.info("register service = {}",rm.name());
            }
            if (bc.length > 0) {
                ArrayBlockingQueue<Object> beanQueue = new ArrayBlockingQueue<>(bc[1]);
                beanQueue.add(handler);
                for (int i = 1; i < bc.length; i++) {
                    beanQueue.add(SpringContext.getBean(path));
                }
                ctx.RegisterHandler(path, handler, beanQueue);
            } else {
                ArrayBlockingQueue<Object> beanQueue = new ArrayBlockingQueue<>(1);
                beanQueue.add(handler);
                ctx.RegisterHandler(path, handler, beanQueue);
            }
        }
    }

    @Override
    public void RemoveHandler(String path) {

    }


    @Override
    public void RegisterHandler(String path, HandlerController handler, ArrayBlockingQueue queue) {
        RequestMapping rm = handler.getClass().getAnnotation(RequestMapping.class);
        if (rm == null) {
            handlers.put("/" + path, queue);
        } else {
            handlers.put(rm.name(), queue);
        }
    }

    @Override
    public HandlerMethod GetHandler(String path) {
        ArrayBlockingQueue beanQueue = null;
        Method m = null;
        String beanPath = "";
        for (String key : handlers.keySet()) {
            if (path.startsWith(key)) {
                ConcurrentHashMap<String, Method> hms = handlerMap.get(key);
                m = hms.get(path.replace(key, ""));
                if (m != null) {
                    beanQueue = handlers.get(key);
                    beanPath = key;
                    break;
                }
            }
        }
        if (beanQueue == null) {
            logger.error("Service is not registered service = {} ", path);
            return null;
        }
        Object bean = null;
        try {
            bean = beanQueue.take();
        } catch (InterruptedException e) {
            logger.error("Failed to get service service = {} err = {}", path, e.getMessage());
            return null;
        }
        if (m == null) {
            logger.error("Get method failed service = {} ", path);
            return null;
        }
        HandlerMethod hm = new HandlerMethod(bean, m, beanPath);
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
        logger.info("start register method");
        Map<String, HandlerController> handlers = SpringContext.getBeansOfType(HandlerController.class);
        for (Map.Entry<String, HandlerController> entry : handlers.entrySet()) {
            logger.info("register method = {}",entry.getKey());
            ctx.RegisterMethod(entry.getKey(), entry.getValue(), bc);
        }
        logger.info("start register task");
        Map<String, DefaultTaskServer> tasks = SpringContext.getBeansOfType(DefaultTaskServer.class);
        for (Map.Entry<String, DefaultTaskServer> entry : tasks.entrySet()) {
            logger.info("register task = {}",entry.getKey());
            ArrayBlockingQueue<DefaultTaskServer> queue = new ArrayBlockingQueue<>(2000);
            queue.add(entry.getValue());
            for (int i = 1; i < 2000; i++) {
                queue.add((DefaultTaskServer) SpringContext.getBean(entry.getKey()));
            }
            ctx.RegisterTask(entry.getValue(), queue);
        }

    }

    @Override
    public void RegisterTask(DefaultTaskServer task, ArrayBlockingQueue<DefaultTaskServer> queue) {
        TaskMapping taskMapping = task.getClass().getAnnotation(TaskMapping.class);
        for (String path : taskMapping.paths()) {
            tasks.put(path, queue);
        }

    }

    @Override
    public DefaultTaskServer GetTask(String path) {
        ArrayBlockingQueue<DefaultTaskServer> queue = tasks.get(path);
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
