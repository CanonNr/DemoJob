package cn.lksun.demojob.core.service;

import cn.lksun.demojob.core.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DemoJob {

    @Value("${demojob.admin.url}")
    private String adminUrl;

    public boolean exec(String AppName,String HandleName,long time,Object... args){
        RestTemplate restTemplate = new RestTemplate();
        Task task = new Task(AppName,HandleName,time,args);
        String requestUrl = adminUrl+"/demo-job/exec";
        try {
            restTemplate.put(requestUrl,task);
            return true;
        }catch (Exception e){
            log.error("error{}",e.getMessage());
            return false;
        }
    }

}
