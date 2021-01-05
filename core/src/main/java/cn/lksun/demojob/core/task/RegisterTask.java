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
import java.net.InetAddress;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 首次注册
 */
@Slf4j
public class RegisterTask implements CommandLineRunner, Ordered {

    private static final ThreadPoolExecutor poll = new ThreadPoolExecutor(3,
            3,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),new NamedThreadFactory("First Register Worker"));

    @Value("${demojob.admin.url}")
    private String adminUrl;

    @Value("${demojob.client.name}")
    private String name;

    @Value("${server.port}")
    private String port;

    @Resource
    Map<String, Handle> handleMap;

    @Resource
    Node node;

    RestTemplate restTemplate = new RestTemplate();

    private final String NID = UUID.randomUUID().toString().replace("-","").toLowerCase();

    @Override
    public void run(String... args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                poll.execute(new Runnable() {
                    @Override
                    public void run() {
                        doRegister();
                    }
                });
            }
        },0,3000);
    }

    private void doRegister(){
        String adminRegisterPath = "/demo-job/register";
        String url = adminUrl+ adminRegisterPath;
        try{
            restTemplate.postForEntity(url, node, String.class);
            log.info("Node Report Success");
        }catch (Exception e){
            log.error("Report Error - Address:{} ,Message:{}",url,e.getMessage());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
