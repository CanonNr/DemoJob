package cn.lksun.demojob.admin.worker;

import cn.lksun.demojob.admin.constant.RedisTableName;
import cn.lksun.demojob.admin.entity.Task;
import cn.lksun.demojob.admin.service.execute.TaskExecute;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class PullTask implements CommandLineRunner, Ordered {

    @Resource
    RedisTemplate<String, Serializable> redisTemplate;

    @Resource
    TaskExecute taskExecute;

    private static final ThreadPoolExecutor poll = new ThreadPoolExecutor(60,
            60,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),new NamedThreadFactory("Job Worker"));

    /**
     * todo 此处需要加一个锁防止第一个线程还在轮询中新的线程又去轮询造成重复消费。
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        BoundZSetOperations<String, Serializable> timeRankOps = redisTemplate.boundZSetOps(RedisTableName.REDIS_TIME_RANK_TABLE_NAME);

        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps(RedisTableName.REDIS_TASK_INFO_TABLE_NAME);

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                poll.execute(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            log.info("working...");
                            // 取第一条数据
                            Set<Serializable> range = timeRankOps.range(0L, 0L);
                            // 如果结果不为空且长度大于1
                            if (range != null && range.size() > 0) {
                                // 获取到任务ID
                                String taskId = (String) range.toArray()[0];
                                // 获取当前任务的时间戳
                                int score = Objects.requireNonNull(timeRankOps.score(taskId)).intValue();
                                // 判断是否过期
                                if (score <= System.currentTimeMillis() / 1000){
                                    Object task = hashOps.get(taskId);
                                    if (task != null){
                                        log.info("开始执行...{}",taskId);
                                        Task taskObject = JSONObject.parseObject(task.toString(),Task.class);
                                        taskExecute.exec(taskId,taskObject);
                                    }
                                    timeRankOps.remove(taskId);
                                    hashOps.delete(taskId);
                                }else{
                                    // 如果没有过期则结束循环
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                    }
                });
            }
        }, 1000, 500);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
