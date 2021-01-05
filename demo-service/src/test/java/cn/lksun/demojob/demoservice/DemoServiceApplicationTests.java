package cn.lksun.demojob.demoservice;

import cn.lksun.demojob.core.service.DemoJob;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoServiceApplicationTests {

    @Resource
    DemoJob demoJob;

    @Test
    void contextLoads() {
        demoJob.exec();
    }

}
