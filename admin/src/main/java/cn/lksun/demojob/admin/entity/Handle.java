package cn.lksun.demojob.admin.entity;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;

@Data
public class Handle {

    public String handleName;

    public String handleDescription;

    public String className;

    public String methodName;

    public String methodString;

    public List<Class<?>> parameterTypes;

    protected Object getObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(className);
        return aClass.newInstance();
    }

    protected Method getMethod() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> aClass = Class.forName(className);
        return aClass.getMethod(methodName);
    }

    protected void invoke(Object... args){
        try {
            this.getMethod().invoke(this.getObject(),args);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Handle(String HandleName,String handleDescription , String methodString, String className,String methodName,List<Class<?>> parameterTypes){
        // 执行器名称
        this.handleName = HandleName;
        // 执行器介绍
        this.handleDescription = handleDescription;
        // 方法名
        this.className = className;
        // 类路径
        this.methodName = methodName;

        this.methodString = methodString;

        this.parameterTypes = parameterTypes;
    }

    Handle(){

    }
}
