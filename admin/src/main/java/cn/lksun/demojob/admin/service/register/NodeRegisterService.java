package cn.lksun.demojob.admin.service.register;

import cn.lksun.demojob.admin.entity.Node;
import cn.lksun.demojob.admin.entity.NodeGroup;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class NodeRegisterService {

    public boolean doRegister(Node node){

        CacheManager cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("NodeList");
        Element nodeList = cache.get(node.appName);
        if (nodeList == null){
            // 若不存在则直接添加
            NodeGroup group = new NodeGroup();
            node.lastReportTime = new Date();
            node.heartbeat = 1;
            if(group.nodes.add(node)){
                Element element = new Element(node.appName,group);
                cache.put(element);
                return true;
            }
        }else{
            // 当前组所有节点
            NodeGroup nodeGroup = (NodeGroup) nodeList.getObjectValue();
            int index = isExistNode(nodeGroup.nodes,node);
            if (index >= 0) {
                Node temNode = nodeGroup.nodes.get(index);
                temNode.heartbeat += 1;
                temNode.lastReportTime = new Date();
                Element element = new Element(node.appName,nodeGroup);
                cache.put(element);
                return true;
            }else {
                // 不存在
                node.lastReportTime = new Date();
                node.heartbeat = 1;
                if (nodeGroup.nodes.add(node)){
                    Element element = new Element(node.appName,nodeGroup);
                    cache.put(element);
                    return true;
                }
            }
        }
        return false;
    }

    private Integer isExistNode(List<Node> list,Node node){
        int index = 0;
        for (Node item:list) {
            index++;
            if (item.nid.equals(node.nid) && item.url.equals(node.url)){
                return index-1;
            }
        }
        return -1;
    }
}
