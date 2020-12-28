package cn.lksun.demojob.core.task;

import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.entity.Node;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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

    private final Node node = new Node();

    @Resource
    Map<String, Handle> handleMap;

    RestTemplate restTemplate = new RestTemplate();

    private boolean registerStatus = false;

    @Override
    public void run(String... args) throws Exception {
        node.setAppName(name);
        node.setUrl(InetAddress.getLocalHost().getHostAddress() + ":" + port);
        node.setHandleMap(handleMap);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                poll.execute(new Runnable() {
                    @Override
                    public void run() {
                        doRegister();
                        if (registerStatus) timer.cancel();
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
            registerStatus = true;
            log.info("Job Register Success");
        }catch (Exception e){
            log.error("Register Error - Address:{} ,Message:{}",url,e.getMessage());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
