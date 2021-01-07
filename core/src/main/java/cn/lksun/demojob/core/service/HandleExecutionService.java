package cn.lksun.demojob.core.service;

import java.lang.reflect.Method;
import java.util.Arrays;


public class HandleExecutionService {

    public String className;

    public String methodName;

    public Class<?> clz;

    public HandleExecutionService(String className, String methodName) throws ClassNotFoundException {
        clz = Class.forName(className);
        this.className = className;
        this.methodName = methodName;
    }

    public Object getObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return clz.newInstance();
    }

    public Method getMethod(String methodName,Object[] parameterTypes) throws ClassNotFoundException, NoSuchMethodException {
        Method[] methods = clz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Object[] tempParameterTypes = Arrays.stream(method.getParameterTypes()).toArray();
                if (Arrays.equals(tempParameterTypes, parameterTypes)) {
                    return method;
                }
            }
        }
        return null;
    }
}
