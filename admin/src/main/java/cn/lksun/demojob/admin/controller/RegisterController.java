package cn.lksun.demojob.admin.controller;

import cn.lksun.demojob.admin.entity.Node;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("demo-job")
public class RegisterController {
    @PostMapping("register")
    public void register(@RequestBody Node node){
        log.info(node.toString());
    }
}
