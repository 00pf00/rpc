package cn.bupt.edu.server.anotate;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskMapping {
    String[] paths();
}
