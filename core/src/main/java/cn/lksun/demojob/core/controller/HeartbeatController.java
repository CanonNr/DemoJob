package cn.lksun.demojob.core.controller;

import cn.lksun.demojob.core.entity.Node;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo-job/heartbeat")
public class HeartbeatController {

    @Resource
    Node node;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Node node(){
        return node;
    }
}
