package cn.lksun.demojob.core.bean;

import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.entity.HandleFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HandleConfig {

    @Bean
    public Map<String, Handle> handleMap(){
        return new HashMap<String, Handle>();
    }
}
