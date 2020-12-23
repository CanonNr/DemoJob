package cn.lksun.demojob.demoservice;

import cn.lksun.demojob.core.annotation.EnableDemoJob;
import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.handler.aspect.ScannerComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootApplication
@EnableDemoJob
public class DemoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
    }

}
