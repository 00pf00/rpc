package cn.bupt.edu.context.springContext;

import cn.bupt.edu.controller.HandlerController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName){
        if (applicationContext.containsBean(beanName)){
            return (T) applicationContext.getBean(beanName);
        }else {
            return null;
        }

    }

    public static <T> Map<String, HandlerController> getBeansOfType(Class<HandlerController> baseType){
        return applicationContext.getBeansOfType(baseType);
    }
}
