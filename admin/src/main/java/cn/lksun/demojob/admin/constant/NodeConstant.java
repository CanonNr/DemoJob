package cn.lksun.demojob.admin.constant;

public class NodeConstant {
    // 正常节点
    public static final Integer STATUS_NORMAL_OUTAGE = 1;

    // 疑似宕机,降低优先级
    public static final Integer STATUS_MAYBE_OUTAGE = 2;

    // 确认宕机，不再委派
    public static final Integer STATUS_ASCERTAIN_OUTAGE = 3;

    // 客户端执行器工厂落地路径
    public static final String NODE_HANDLE_FACTORY_PATH = "/demo-job/exec/factory";
}
