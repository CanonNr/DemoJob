package cn.lksun.demojob.admin.entity;

import lombok.Data;
import sun.util.calendar.BaseCalendar;

import java.util.Date;
import java.util.Map;

@Data
public class Node {

    // 正常节点
    protected final Integer STATUS_NORMAL_OUTAGE = 1;

    // 疑似宕机,降低优先级
    protected final Integer STATUS_MAYBE_OUTAGE = 2;

    // 确认宕机，不再委派
    protected final Integer STATUS_ASCERTAIN_OUTAGE = 3;

    // 节点名,组名
    // 由该字段对多节点注册时进行分组
    public String appName;

    public String url;

    public Map<String, Handle> handleMap;

    public Integer status;

    // 续约次数
    public Integer heartbeat;

    @Override
    protected Object clone() {
        Node node = null;
        try{
            node = (Node)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return node;
    }

}
