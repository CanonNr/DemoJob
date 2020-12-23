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
    @Before("@annotation(jobHandle)")// 拦截被TestAnnotation注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)，具体百度一下资料一大堆
    public void beforeTest(JoinPoint point, JobHandle jobHandle) throws Throwable {
        Class<?> clz = Class.forName(point.getSignature().getDeclaringType().getName());
        Object object = clz.newInstance();
        Method method = clz.getMethod(point.getSignature().getName());
        Object invoke = method.invoke(object);
        System.out.println(invoke);
    }
}
