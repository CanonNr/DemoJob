package cn.lksun.demojob.core.handler.aspect;



import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.handler.annotation.JobHandle;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class ScannerComponent implements BeanPostProcessor {

    @Resource
    Map<String, Handle> handleMap;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
            if (method != null){
                JobHandle jobHandle = AnnotationUtils.findAnnotation(method, JobHandle.class);
                if (jobHandle!=null) {
                    String handleName = jobHandle.Name();
                    String handleDescription = jobHandle.Description();
                    String className = method.getDeclaringClass().getName();
                    String methodName = method.getName();
                    Handle handle = new Handle(handleName,handleDescription,method.toString(),className, methodName);
                    handleMap.put(handleName,handle);
                }
            }
        }
        return bean;
    }
}
