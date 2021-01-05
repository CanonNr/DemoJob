package cn.lksun.demojob.core.bean;

import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.entity.HandleFactory;
import cn.lksun.demojob.core.entity.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class HandleConfig {

    @Value("${server.port}")
    private String port;

    @Value("${demojob.admin.url}")
    private String AdminUrl;

    @Value("${demojob.client.name:demoJobDefaultName}")
    private String name;

    private final String NID = UUID.randomUUID().toString().replace("-","").toLowerCase();

    @Bean
    public Map<String, Handle> handleMap(){
        return new HashMap<String, Handle>();
    }

    @Bean
    public Node node(){
        Node node = new Node();

        if (name.equals("demoJobDefaultName")){
            return node;
        }

        node.setNid(NID);
        node.setAdminUrl(AdminUrl);
        node.setAppName(name);
        try {
            node.setUrl(InetAddress.getLocalHost().getHostAddress() + ":" + port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        node.setHandleMap(handleMap());
        return node;
    }
}
