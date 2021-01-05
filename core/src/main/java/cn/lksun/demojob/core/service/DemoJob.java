package cn.lksun.demojob.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DemoJob {

    @Value("${demojob.admin.url}")
    private String adminUrl;

    public void exec(){
        System.out.println(adminUrl);
    }

}
