package cn.lksun.demojob.core.entity;

import lombok.Data;
import java.util.Map;

@Data
public class Node {

    // 节点名,组名
    // 由该字段对多节点注册时进行分组
    public String appName;

    public String url;

    public Map<String, Handle> handleMap;

    // 心跳值,用于生命检测
    // 0 正常节点
    // 2 疑似宕机，降低优先级
    // 3 确认宕机，不再委派
    // 5 删除节点
    public Integer heartbeat;

}
