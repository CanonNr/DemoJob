package cn.lksun.demojob.admin.entity;

import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class Task {

    public String appName;

    public Integer time;

    public List<Object> args;

    public Task(String appName, Integer time, Object... args){
        this.appName = appName;
        this.time = time;
        this.args = Arrays.asList(args);
    }

    public Task(){

    }

}
