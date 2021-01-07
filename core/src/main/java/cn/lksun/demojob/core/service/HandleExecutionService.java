package cn.lksun.demojob.core.service;

import java.lang.reflect.Method;


public class HandleExecutionService {

    public String className;

    public String methodName;

    public HandleExecutionService(String className, String methodName){
        this.className = className;
        this.methodName = methodName;
    }
    public Object getObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(className);
        return aClass.newInstance();
    }

    public Method getMethod() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> aClass = Class.forName(className);
        return aClass.getMethod(methodName);
    }

    public void invoke(Object... args){
        try {
            this.getMethod().invoke(this.getObject(),args);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
