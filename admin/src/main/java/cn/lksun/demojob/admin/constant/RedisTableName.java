package cn.lksun.demojob.admin.constant;

public class RedisTableName {
    /**
     * Redis 任务ID和执行时间的有序集合字段名
     */
    public static final String REDIS_TIME_RANK_TABLE_NAME = "demojob:time-rank";

    /**
     * Redis 任务ID和任务详情的 hashmap 字段名
     */
    public static final String REDIS_TASK_INFO_TABLE_NAME = "demojob:task-info";

}
