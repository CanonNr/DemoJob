package cn.lksun.demojob.demoservice.config;

import cn.lksun.demojob.core.service.DemoJob;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DemoJobConfig {
    @Bean
    public DemoJob demoJob(){
        return new DemoJob();
    }
}
