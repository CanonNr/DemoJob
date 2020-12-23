package cn.lksun.demojob.core.entity;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
public class Handle {

    public String ClassName;

    public Method Method;

    public String HandleName;

    public String ClassPath;

    public Object getObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(ClassPath);
        return aClass.newInstance();
    }

    public void invoke(Object... args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        this.Method.invoke(this.getObject(),args);
    }

    public Handle(String HandleName, Method Method){
        // 执行器名称
        this.HandleName = HandleName;
        // 执行方法
        this.Method = Method;
        // 方法名
        this.ClassName = Method.toString();
        // 类路径
        this.ClassPath = Method.getDeclaringClass().getName();
    }

    Handle(){

    }
}
