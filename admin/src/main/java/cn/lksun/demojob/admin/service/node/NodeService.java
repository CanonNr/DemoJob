package cn.lksun.demojob.admin.service.node;

import cn.lksun.demojob.admin.entity.Node;
import cn.lksun.demojob.admin.entity.NodeGroup;
import cn.lksun.demojob.admin.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
@Service
public class NodeService {

    @Resource
    CacheService cacheService;

    public Map<String, LinkedList<Node>> getNodeMap(){
        Cache cache = cacheService.getCache();
        Map<String, LinkedList<Node>> map = new HashMap<String,LinkedList<Node>>();
        if(cache != null){
            cache.getKeys().forEach(key->{
                Element nodeList = cache.get(key);
                if (nodeList != null){
                    NodeGroup objectValue = (NodeGroup) nodeList.getObjectValue();
                    map.put(key.toString(),objectValue.nodes);
                }
            });
        }
        return map;
    }

    public NodeGroup getNodes(String appName){
        Cache cache = cacheService.getCache();
        if(cache != null){
            Element nodeList = cache.get(appName);
            if (nodeList != null){
                return (NodeGroup)nodeList.getObjectValue();
            }
        }
        return null;
    }

    public void putNode(String appName,NodeGroup nodeGroup){
        Cache cache = cacheService.getCache();
        Element element = new Element(appName,nodeGroup);
        cache.flush();
        cache.put(element);
    }

    public void deleteNode(String appName,String uid){
        Cache cache = cacheService.getCache();
        if(cache != null){
            Element element = cache.get(appName);
            NodeGroup nodeGroup = (NodeGroup) element.getObjectValue();
            nodeGroup.nodes.removeIf(node-> node.nid.equals(uid));
            Element newElement = new Element(appName,nodeGroup);
            cache.flush();
            cache.put(newElement);
        }
    }

    public void deleteGroup(String appName){
        Cache cache = cacheService.getCache();
        if(cache != null){
            cache.flush();
        }
    }
}
