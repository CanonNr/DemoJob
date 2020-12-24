package cn.lksun.demojob.core.handler.aspect;

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
public class JobHandleAnnAspect {
    @Before("@annotation(jobHandle)")
    public void beforeTest(JoinPoint point, JobHandle jobHandle) throws Throwable {
        Class<?> clz = Class.forName(point.getSignature().getDeclaringType().getName());
        Object object = clz.newInstance();
        Method method = clz.getMethod(point.getSignature().getName());
        Object invoke = method.invoke(object);
        System.out.println(invoke);
    }
}
