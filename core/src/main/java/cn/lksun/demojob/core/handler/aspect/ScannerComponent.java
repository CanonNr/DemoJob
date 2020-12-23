package cn.lksun.demojob.core.handler.aspect;



import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.handler.annotation.JobHandle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
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
            JobHandle jobHandle = AnnotationUtils.findAnnotation(method, JobHandle.class);
            if (null != jobHandle) {
                String handleName = jobHandle.Name();
                Handle handle = new Handle(handleName,method);
                handleMap.put(handleName,handle);
            }
        }
        return bean;
    }
}
