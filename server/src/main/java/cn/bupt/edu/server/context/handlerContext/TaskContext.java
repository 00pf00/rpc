package cn.bupt.edu.server.context.handlerContext;

import cn.bupt.edu.server.context.HandlerContext;
import cn.bupt.edu.server.context.HandlerMethod;
import cn.bupt.edu.server.context.springContext.SpringContext;
import cn.bupt.edu.server.controller.HandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TaskContext implements HandlerContext {
    private final static Logger logger = LoggerFactory.getLogger(TaskContext.class);
    private static TaskContext ctx;
    private static ConcurrentHashMap<String, ArrayBlockingQueue<Object>> beans = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Method>> services = new ConcurrentHashMap<>();

    public static TaskContext getInstance() {
        if (ctx == null) {
            ctx = new TaskContext();
        }
        return ctx;
    }

    @Override
    public void RegisterHandler(Object obj, String path) {
        ConcurrentHashMap<String, Method> controller = new ConcurrentHashMap<>();
        java.lang.reflect.Method[] ms = obj.getClass().getMethods();
        for (int i = 0; i < ms.length; i++) {
            GetMapping mapping = ms[i].getAnnotation(GetMapping.class);
            if (mapping == null) {
                continue;
            }
            String[] values = mapping.value();
            String mp = values[0].substring(1);
            controller.put(mp, ms[i]);
        }
        services.put(path, controller);
    }

    @Override
    public void RemoveHandler(String path) {

    }

    @Override
    public HandlerMethod GetHandler(String path) {
        String[] vs = path.split("/");
//        Object bean = SpringContext.getBean(vs[1]);
        ArrayBlockingQueue beanQueue = beans.get(vs[1]);
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
        ConcurrentHashMap<String, Method> service = services.get(vs[1]);
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
        ArrayBlockingQueue<Object> beanQueue = beans.get(hm.serviceName);
        if (beanQueue == null) {
            logger.error("Service is not registered service = {} ", hm.serviceName);
            return;
        }
        beanQueue.add(hm.object);
    }

    @Override
    public void Handler() {

    }


    public void initContext(int... bc) {
        Map<String, HandlerController> beans = SpringContext.getBeansOfType(HandlerController.class);
        for (Map.Entry<String, HandlerController> entry : beans.entrySet()) {
            ctx.RegisterHandler(entry.getValue(), entry.getKey());
            if (bc.length > 0) {
                ArrayBlockingQueue<Object> beanQueue = new ArrayBlockingQueue<>(bc[1]);
                for (int i = 1; i < bc.length; i++) {
                    beanQueue.add(SpringContext.getBean(entry.getKey()));
                }
                this.beans.put(entry.getKey(), beanQueue);
            } else {
                ArrayBlockingQueue<Object> beanQueue = new ArrayBlockingQueue<>(1);
                beanQueue.add(SpringContext.getBean(entry.getKey()));
                this.beans.put(entry.getKey(), beanQueue);
            }
        }

    }
}
