package cn.lksun.demojob.admin.controller;

import cn.lksun.demojob.admin.entity.Node;
import cn.lksun.demojob.admin.service.node.NodeService;
import cn.lksun.demojob.admin.service.register.NodeRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;


@Slf4j
@RestController
@RequestMapping("demo-job")
public class RegisterController {

    @Resource
    NodeRegisterService register;

    @Resource
    NodeService nodeService;

    @PostMapping("register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody Node node){
        if (!this.register.doRegister(node)){
            return new ResponseEntity<Map<String,Object>>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @GetMapping("test")
    public Map<String, LinkedList<Node>> test(){
        return nodeService.getNodeMap();
    }

}
