package cn.lksun.demojob.demoservice;

import cn.lksun.demojob.core.service.DemoJob;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class DemoServiceApplicationTests {

    @Resource
    DemoJob demoJob;



    @Test
    void contextLoads() {

        long firstTime = (System.currentTimeMillis()/ 1000) + 60;

        for (int i = 0; i < 500; i++) {
            long time = firstTime + (int) (Math.random()*(30-1)+1);
            boolean exec = demoJob.exec("test1", "PrintHandle",time,"uenit");
            System.out.println(exec + " - " + time);
        }

    }



}
