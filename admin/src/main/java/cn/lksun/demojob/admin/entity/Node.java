package cn.lksun.demojob.admin.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Node {

    // 正常节点
    public final Integer STATUS_NORMAL_OUTAGE = 1;

    // 意思宕机,降低优先级
    public final Integer STATUS_MAYBE_OUTAGE = 2;

    // 确认宕机，不再委派
    public final Integer STATUS_ASCERTAIN_OUTAGE = 3;

    // 节点名,组名
    // 由该字段对多节点注册时进行分组
    public String appName;

    public String url;

    public Map<String, Handle> handleMap;

    public Integer status;

    // 续约次数
    public Integer heartbeat;
}
