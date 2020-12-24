package cn.lksun.demojob.admin.worker;

import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
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

    @Resource
    RedisTemplate<String, Serializable> redisTemplate;

    private static final ThreadPoolExecutor poll = new ThreadPoolExecutor(60,
            60,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),new NamedThreadFactory("Job Worker"));


    @Override
    public void run(String... args) throws Exception {
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                poll.execute(new Runnable() {
                    @Override
                    public void run() {

                        BoundZSetOperations<String, Serializable> timeRankOps = redisTemplate.boundZSetOps(TIME_RANK);

                        while (true){
                            Set<Serializable> range = timeRankOps.range(0L, 0L);
                            if (range != null) {
                                String taskId = (String) Arrays.stream(range.toArray()).findFirst().orElse(null);
                                if (taskId != null) {
                                    int score = Objects.requireNonNull(timeRankOps.score(taskId)).intValue();
                                    if (score <= System.currentTimeMillis() / 1000){
                                        // todo
                                        log.info("开始执行...{}",taskId);
                                        timeRankOps.remove(taskId);
                                    }

                                    // 没有需要执行的任务
                                    log.info("end...");
                                    break;
                                }
                            }
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
