package cn.lksun.demojob.core.handler.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JobHandle {

    String Name();

    String Description() default "Job Handle";
}
