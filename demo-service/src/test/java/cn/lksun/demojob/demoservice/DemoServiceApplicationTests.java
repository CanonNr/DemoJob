package cn.lksun.demojob.demoservice;

import cn.lksun.demojob.core.service.DemoJob;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class DemoServiceApplicationTests {

    @Resource
    DemoJob demoJob;



    @Test
    void contextLoads() {
        boolean exec = demoJob.exec("test1", 1,"haha1", 2, 3, 4, "houhou");
        System.out.println(exec);
    }



}
