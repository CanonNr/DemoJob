package cn.lksun.demojob.admin.service.register;

import cn.lksun.demojob.admin.entity.Node;
import cn.lksun.demojob.admin.entity.NodeGroup;
import cn.lksun.demojob.admin.util.Iterables;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class NodeRegister {

    public boolean doRegister(Node node){

        CacheManager cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("NodeList");
        Element nodeList = cache.get(node.appName);
        System.out.println(nodeList);
        if (nodeList == null){
            // 若不存在则直接添加
            NodeGroup group = new NodeGroup();
            group.nodes.add(node);
            Element element = new Element(node.appName,group);
            cache.put(element);
        }else{
            NodeGroup nodeGroup = (NodeGroup) nodeList.getObjectValue();
            nodeGroup.nodes.removeIf(s -> s.url.equals(node.url));
            if (nodeGroup.nodes.add(node)){
                Element element = new Element(node.appName,nodeGroup);
                cache.put(element);
            }
        }
        return true;
    }
}
