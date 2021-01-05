package cn.lksun.demojob.core.controller;

import cn.lksun.demojob.core.entity.Node;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


@RestController
@RequestMapping("/demo-job/node")
public class InfoController {

    @Resource
    Node node;

    @GetMapping(value = "info")
    public Node info(){
        return node;
    }
}
