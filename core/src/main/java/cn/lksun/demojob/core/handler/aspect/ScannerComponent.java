package cn.lksun.demojob.core.handler.aspect;


import cn.lksun.demojob.core.handler.annotation.JobHandle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class ScannerComponent implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
            JobHandle jobHandle = AnnotationUtils.findAnnotation(method, JobHandle.class);
            if (null != jobHandle) {
                // todo
                // 此处会获取所有已注册的方法名
                String ClassName = method.getDeclaringClass().getName();
                System.out.println(method.toString());
                System.out.println(jobHandle.Name());
                System.out.println(ClassName);

                // 以下是执行方法
//                try {
//                    Class<?> aClass = Class.forName(ClassName);
//                    Object object = aClass.newInstance();
//                    method.invoke(object);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }
        return bean;
    }
}
