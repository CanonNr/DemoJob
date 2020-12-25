package cn.lksun.demojob.core.service;

import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.entity.Node;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Slf4j
public class Register implements CommandLineRunner, Ordered {
    @Value("${server.port}")
    private String port;

    @Value("${demojob.admin.url}")
    private String AdminUrl;

    @Value("${demojob.client.name}")
    private String name;

    @Resource
    Map<String, Handle> handleMap;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void run(String... args) throws Exception {
        Node node = new Node();
        node.setAppName(name);
        node.setUrl(InetAddress.getLocalHost().getHostAddress() + ":" + port);
        node.setHandleMap(handleMap);

        String registerPath = "/demo-job/register";
        String registerUrl = AdminUrl + registerPath;

        try{
            restTemplate.postForEntity(registerUrl, node, String.class);
            log.info("Job Register Success");
        }catch (Exception e){
            log.error("Register Error - Address:{} ,Message:{}",registerUrl,e.getMessage());
            System.exit(10);
        }
    }
    public void doRegister(){

    }


    @Override
    public int getOrder() {
        return 0;
    }
}
