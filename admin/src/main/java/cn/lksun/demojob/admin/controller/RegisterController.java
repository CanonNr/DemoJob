package cn.lksun.demojob.admin.controller;

import cn.lksun.demojob.admin.entity.Node;
import cn.lksun.demojob.admin.entity.NodeGroup;
import cn.lksun.demojob.admin.service.register.NodeRegister;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("demo-job")
public class RegisterController {

    @Resource
    NodeRegister register;

    @PostMapping("register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody Node node){
        if (!this.register.doRegister(node)){
            return new ResponseEntity<Map<String,Object>>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }


    @GetMapping("test")
    public NodeGroup test(){
        CacheManager cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("NodeList");
        Element nodeList = cache.get("test999");
        return (NodeGroup)nodeList.getObjectValue();
    }
}
