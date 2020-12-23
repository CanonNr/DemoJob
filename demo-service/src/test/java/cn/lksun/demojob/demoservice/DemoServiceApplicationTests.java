package cn.lksun.demojob.demoservice;

import cn.lksun.demojob.core.entity.Handle;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class DemoServiceApplicationTests {

    @Resource
    Map<String, Handle> handleMap;

    @Test
    void contextLoads() {
        System.out.println(handleMap);
    }

}
