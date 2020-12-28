package cn.lksun.demojob.core.controller;

import cn.lksun.demojob.core.entity.Handle;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/demo-job/handle")
public class HandleController {

    @Resource
    Map<String, Handle> handleMap;

    @GetMapping("")
    public Map<String, Handle> getHandleMap(){
        return handleMap;
    }
}
