package cn.lksun.demojob.core.controller;


import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.entity.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;


@RestController
@RequestMapping("/demo-job/node")
public class InfoController {
    @Value("${server.port}")
    private String port;

    @Value("${demojob.admin.url}")
    private String AdminUrl;

    @Value("${demojob.client.name}")
    private String name;

    @Resource
    Map<String, Handle> handleMap;

    @GetMapping(value = "info")
    public Node info() throws UnknownHostException {
        Node node = new Node();
        node.setAppName(name);
        node.setUrl(InetAddress.getLocalHost().getHostAddress() + ":" + port);
        node.setHandleMap(handleMap);
        return node;
    }
}
