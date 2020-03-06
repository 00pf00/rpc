package cn.bupt.edu.context.handlerContext;

import cn.bupt.edu.context.HandlerContext;
import cn.bupt.edu.context.HandlerMethod;
import cn.bupt.edu.context.springContext.SpringContext;
import cn.bupt.edu.controller.HandlerController;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskContext implements HandlerContext {
    private static TaskContext ctx;
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Method>> services;

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
        Object bean = SpringContext.getBean(vs[1]);
        Method m = services.get(vs[1]).get(vs[2]);
        HandlerMethod hm = new HandlerMethod(bean, m, vs[1]);
        return hm;
    }

    @Override
    public void Handler() {

    }

    public void initContext() {
        Map<String, HandlerController> beans = SpringContext.getBeansOfType(HandlerController.class);
        for (Map.Entry<String, HandlerController> entry : beans.entrySet()) {
            ctx.RegisterHandler(entry.getValue(), entry.getKey());
        }
//        Object device = SpringContext.getBean("device");
//        ctx.RegisterHandler(device,"device");

    }

    public static TaskContext getInstance() {
        if (ctx == null) {
            services = new ConcurrentHashMap<>();
            ctx = new TaskContext();
        }
        return ctx;
    }
}
