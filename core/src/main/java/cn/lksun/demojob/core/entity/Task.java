package cn.lksun.demojob.core.entity;

import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class Task {

    public String appName;

    public String handleName;

    public long time;

    public List<Object> args;

    public Task(String appName,String handleName,long time, Object... args){
        this.appName = appName;
        this.time = time;
        this.handleName = handleName;
        this.args = Arrays.asList(args);
    }

    public Task(){

    }
}
