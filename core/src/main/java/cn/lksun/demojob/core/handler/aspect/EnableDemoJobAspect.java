package cn.lksun.demojob.core.handler.aspect;

import cn.lksun.demojob.core.annotation.EnableDemoJob;
import cn.lksun.demojob.core.handler.annotation.JobHandle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Order(1)
@Component
public class EnableDemoJobAspect {
    @Before("@annotation(demoJob)")
    public void beforeTest(JoinPoint point, EnableDemoJob demoJob){
        System.out.println(123);
    }
}
