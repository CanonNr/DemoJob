package cn.lksun.demojob.core.service;

import cn.lksun.demojob.core.entity.Handle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;

import javax.annotation.Resource;
import java.util.Map;

public class Register implements CommandLineRunner, Ordered {
    @Value("${demojob.admin.url}")
    private String AdminUrl;

    @Value("${demojob.client.name}")
    private String name;

    @Resource
    Map<String, Handle> handleMap;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(AdminUrl);
        System.out.println(name);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
