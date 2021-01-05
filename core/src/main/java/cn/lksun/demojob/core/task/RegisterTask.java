package cn.lksun.demojob.core.task;

import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.entity.Node;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RegisterTask implements CommandLineRunner, Ordered {

    private static final ThreadPoolExecutor poll = new ThreadPoolExecutor(3,
            3,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),new NamedThreadFactory("First Register Worker"));

    @Resource
    Node node;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void run(String... args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                poll.execute(new Runnable() {
                    @Override
                    public void run() {
                        if(node.appName == null){
                            timer.cancel();
                        }
                        doRegister();
                    }
                });
            }
        },0,3000);
    }

    private void doRegister(){
        if (node.adminUrl != null){
            String adminRegisterPath = "/demo-job/register";
            String url = node.adminUrl+ adminRegisterPath;
            try{
                restTemplate.postForEntity(url, node, String.class);
                log.info("Node Report Success");
            }catch (Exception e){
                log.error("Report Error - Address:{} ,Message:{}",url,e.getMessage());
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
