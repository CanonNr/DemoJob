package cn.lksun.demojob.admin.controller;

import cn.lksun.demojob.admin.entity.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("demo-job")
public class RegisterController {
    @PostMapping("register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody Node node){
        System.out.println(node);
        return new ResponseEntity<Map<String,Object>>(new HashMap<>(), HttpStatus.BAD_REQUEST);
    }
}
