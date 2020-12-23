package cn.lksun.demojob.core.worker;

import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class PullTask implements CommandLineRunner, Ordered {


    private static final ThreadPoolExecutor poll = new ThreadPoolExecutor(10,
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
                        log.info("working...");
                    }
                });
            }
        }, 0, 5000);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
