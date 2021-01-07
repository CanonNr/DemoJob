package cn.lksun.demojob.admin.worker;

import cn.lksun.demojob.admin.service.heartbeat.HeartbeatService;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class HeartbeatTask implements CommandLineRunner, Ordered {

    @Resource
    HeartbeatService heartbeatService;

    public static final ThreadPoolExecutor poll = new ThreadPoolExecutor(60,
            60,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),new NamedThreadFactory("Heartbeat Worker"));

    @Override
    public void run(String... args) throws Exception {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                poll.execute(new Runnable(){
                    @Override
                    public void run() {
                        heartbeatService.nodeScan();
                    }
                });

            }
        },30000,30000);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
