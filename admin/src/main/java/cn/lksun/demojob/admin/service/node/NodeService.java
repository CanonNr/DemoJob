package cn.lksun.demojob.admin.service.node;

import cn.lksun.demojob.admin.entity.Node;
import cn.lksun.demojob.admin.entity.NodeGroup;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
@Service
public class NodeService {

    public Map<String, LinkedList<Node>> getNodeMap(){
        Map<String, LinkedList<Node>> map = new HashMap<String,LinkedList<Node>>();
        CacheManager cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("NodeList");
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

    public void putNode(String appName,NodeGroup nodeGroup){
        CacheManager cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("NodeList");
        Element element = new Element(appName,nodeGroup);
        cache.flush();
        cache.put(element);
    }
}
