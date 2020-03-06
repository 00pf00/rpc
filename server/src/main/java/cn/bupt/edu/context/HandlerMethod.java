package cn.bupt.edu.context;

import java.lang.reflect.Method;

public class HandlerMethod {
    public Object object;
    public Method method;
    public String serviceName;
    public HandlerMethod(Object obj ,Method m,String name){
        this.object = obj;
        this.method = m;
        this.serviceName = name;
    }
}
