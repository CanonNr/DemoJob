package cn.lksun.demojob.admin.entity;

import lombok.Data;
import sun.util.calendar.BaseCalendar;

import java.util.Date;
import java.util.Map;

@Data
public class Node {

    public String nid;

    // 节点名,组名
    // 由该字段对多节点注册时进行分组
    public String appName;

    public String url;

    public Map<String, Handle> handleMap;

    public Integer status;

    // 续约次数
    public Integer heartbeat = 0;

    // 上次报告时间
    public Date lastReportTime;

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
