package cn.lksun.demojob.admin.worker;

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

    public final String TIME_RANK = "demojob:time-rank";

    public final String TASK_INFO = "demojob:task-info";

    @Resource
    RedisTemplate<String, Serializable> redisTemplate;

    private static final ThreadPoolExecutor poll = new ThreadPoolExecutor(60,
            60,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),new NamedThreadFactory("Job Worker"));


    @Override
    public void run(String... args) throws Exception {

        BoundZSetOperations<String, Serializable> timeRankOps = redisTemplate.boundZSetOps(TIME_RANK);

        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps(TASK_INFO);

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                poll.execute(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            Set<Serializable> range = timeRankOps.range(0L, 0L);
                            if (range != null) {
                                String taskId = (String) Arrays.stream(range.toArray()).findFirst().orElse(null);
                                if (taskId != null) {
                                    int score = Objects.requireNonNull(timeRankOps.score(taskId)).intValue();
                                    if (score <= System.currentTimeMillis() / 1000){
                                        log.info("开始执行...{}",taskId);
                                        if (hashOps.get(taskId) != null){
                                            // 存在则执行
                                            log.info("{}存在",taskId);
                                        }
                                        timeRankOps.remove(taskId);
                                        hashOps.delete(taskId);
                                    }
                                }
                                log.info("working...");
                                break;
                            }
                        }
                    }
                });
            }
        }, 0, 1500);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
