package cn.lksun.demojob.admin.service.heartbeat;

import cn.lksun.demojob.admin.constant.NodeStatus;
import cn.lksun.demojob.admin.entity.Node;
import cn.lksun.demojob.admin.service.node.NodeService;
import cn.lksun.demojob.admin.util.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
@Service
public class HeartbeatService {

    private final String CLIENT_HEARTBEAT_PATH = "/demo-job/heartbeat";

    @Resource
    NodeService nodeService;

    RestTemplate restTemplate = new RestTemplate();

    public void nodeScan(){
        Map<String, LinkedList<Node>> nodeMap = nodeService.getNodeMap();
        for (String key : nodeMap.keySet()) {
            LinkedList<Node> nodes = nodeMap.get(key);

            Iterables.forEach(nodes,(index,node)->{
                String nodeUrl = node.url+CLIENT_HEARTBEAT_PATH;
                try {
                    restTemplate.put(nodeUrl,null);
                } catch (RestClientException e) {
                    log.info("Find Death Node : {} - {}",node.appName,node.url);
                    nodes.remove(index);
                }
            });
        }
    }
}
