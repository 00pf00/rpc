package cn.bupt.edu.server.context.handlerContext;

import cn.bupt.edu.base.protocol.ProtocolReqMsgProto;
import cn.bupt.edu.base.util.Util;
import cn.bupt.edu.server.anotate.HandlerMapping;
import cn.bupt.edu.server.anotate.TaskMapping;
import cn.bupt.edu.server.context.HandlerContext;
import cn.bupt.edu.server.context.HandlerMethod;
import cn.bupt.edu.server.context.TaskContext;
import cn.bupt.edu.server.context.springContext.SpringContext;
import cn.bupt.edu.server.controller.HandlerController;
import cn.bupt.edu.server.task.DefaultServerTask;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TaskHandlerContext implements HandlerContext, TaskContext {
    private final static Logger logger = LoggerFactory.getLogger(TaskHandlerContext.class);
    private static TaskHandlerContext ctx;
    private static ConcurrentHashMap<String, ArrayBlockingQueue<Object>> handlers = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Class<? extends DefaultServerTask>> tasks = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Method>> handlerMap = new ConcurrentHashMap<>();

    public static TaskHandlerContext getInstance() {
        if (ctx == null) {
            ctx = new TaskHandlerContext();
        }
        return ctx;
    }

    @Override
    public void RegisterMethod(String path, HandlerController handler, int... bc) {
        if (AopUtils.isCglibProxy(handler)) {
            logger.info("controller is cglib object ");
            try {
                handler = (HandlerController) getCglibProxyTargetObject(handler);
            } catch (Exception e) {
                logger.error("get cglib target object fail err = {}", e.toString());
                return;
            }
        }
        ConcurrentHashMap<String, Method> controller = new ConcurrentHashMap<>();
        java.lang.reflect.Method[] ms = handler.getClass().getMethods();
        boolean flag = false;
        for (int i = 0; i < ms.length; i++) {

            HandlerMapping handlerMapping = ms[i].getAnnotation(HandlerMapping.class);
            if (handlerMapping == null) {
                continue;
            }
            logger.info("method path = {}", handlerMapping.path());
            controller.put(handlerMapping.path(), ms[i]);
            if (!flag) {
                flag = true;
            }
        }
        if (flag) {
            RequestMapping rm = handler.getClass().getAnnotation(RequestMapping.class);
            if (rm == null) {
                path = "/" + path;
                logger.info("register service = {}", path);
            } else {
                path = rm.value()[0];
                logger.info("register service = {}", rm.value()[0]);
            }
            handlerMap.put(path, controller);
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
        handlers.put(path, queue);
    }

    @Override
    public HandlerMethod GetHandler(String path) {
        path = Util.replace(path);
        ArrayBlockingQueue beanQueue = null;
        Method m = null;
        String beanPath = "";
        for (String key : handlers.keySet()) {
            if (path.startsWith(key)) {
                ConcurrentHashMap<String, Method> hms = handlerMap.get(key);
                m = hms.get(path.substring(key.length()));
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
            logger.info("register method = {}", entry.getKey());
            ctx.RegisterMethod(entry.getKey(), entry.getValue(), bc);
        }
        logger.info("start register task");
        Map<String, DefaultServerTask> tasks = SpringContext.getBeansOfType(DefaultServerTask.class);
        for (Map.Entry<String, DefaultServerTask> entry : tasks.entrySet()) {
            logger.info("register task = {}", entry.getKey());
            ctx.RegisterTask(entry.getValue());
        }

    }

    @Override
    public void RegisterTask(DefaultServerTask task) {
        Class<? extends DefaultServerTask> tclass = task.getClass();
        TaskMapping taskMapping = task.getClass().getAnnotation(TaskMapping.class);
        for (String path : taskMapping.paths()) {
            tasks.put(path, tclass);
        }

    }

    @Override
    public DefaultServerTask GetTask(String path, ProtocolReqMsgProto.ProtocolReqMsg req, ChannelHandlerContext ctx) {
        Class<? extends DefaultServerTask> st = tasks.get(path);
        Constructor stc = null;
        try {
            stc = st.getDeclaredConstructor(ProtocolReqMsgProto.ProtocolReqMsg.class, ChannelHandlerContext.class);
        } catch (Exception e) {
            logger.error("get defaultTaskServer constructor fail ! err = {}", e.toString());
            return null;
        }
        stc.setAccessible(true);
        Object sto = null;
        try {
            sto = stc.newInstance(req, ctx);
        } catch (Exception e) {
            logger.error("get defaultTaskServer object fail ! err = {} ", e.toString());
            return null;
        }
        if (sto instanceof DefaultServerTask) {
            return (DefaultServerTask) sto;
        }
        return null;
    }

    private Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        logger.info("target class name = {}", target.getClass().getName());
        return target;
    }
}
