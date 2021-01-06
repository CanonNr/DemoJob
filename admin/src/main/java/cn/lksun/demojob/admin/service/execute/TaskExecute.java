package cn.lksun.demojob.admin.service.execute;

import cn.lksun.demojob.admin.entity.Handle;
import cn.lksun.demojob.admin.entity.Node;
import cn.lksun.demojob.admin.entity.NodeGroup;
import cn.lksun.demojob.admin.entity.Task;
import cn.lksun.demojob.admin.service.node.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.LinkedList;
import cn.lksun.demojob.admin.constant.NodeConstant;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class TaskExecute {

    @Resource
    NodeService nodeService;

    public void exec(String taskId,Task task){

        NodeGroup nodeGroup = nodeService.getNodes(task.appName);

        LinkedList<Node> nodes = nodeGroup.nodes;

        if (nodes.size() <= 0){
            log.error("TaskID {} - Node Not Found {}",taskId,task.appName);
        }

        // 确定节点 (第一版先取第一个节点)
        Node node = nodes.getFirst();
        Handle handle = node.handleMap.get(task.handleName);
        if (handle == null){
            log.error("TaskID {} - {} Handle Not Found ",taskId,task.handleName);
        }
        // 发送 同步
        String url = "http://" + node.url + NodeConstant.NODE_HANDLE_FACTORY_PATH;
        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> remoteExec = restTemplate.postForEntity(url, task, String.class);
            String body = remoteExec.getBody();
            log.info("TaskID {} - Task Remote Execution Successfully",taskId);
        }catch (Exception e){
            log.error("TaskID {} - Task Remote Execution Failed - {}",taskId,e.getMessage());
        }
    }
}
