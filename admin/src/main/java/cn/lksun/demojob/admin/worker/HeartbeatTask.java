package cn.lksun.demojob.admin.worker;

import cn.lksun.demojob.admin.service.heartbeat.HeartbeatService;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;

import javax.annotation.Resource;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HeartbeatTask implements CommandLineRunner, Ordered {

    @Resource
    HeartbeatService heartbeatService;

    private final ThreadPoolExecutor pool = new ThreadPoolExecutor(10,
            5,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),new NamedThreadFactory("Heartbeat Worker"));

    @Override
    public void run(String... args) throws Exception {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                heartbeatService.nodeScan();
            }
        },2000,5000);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
