package cn.lksun.demojob.admin.constant;

public class NodeStatus {
    // 正常节点
    public static final Integer STATUS_NORMAL_OUTAGE = 1;

    // 疑似宕机,降低优先级
    public static final Integer STATUS_MAYBE_OUTAGE = 2;

    // 确认宕机，不再委派
    public static final Integer STATUS_ASCERTAIN_OUTAGE = 3;
}
