package cn.lksun.demojob.admin.controller;

import cn.lksun.demojob.admin.entity.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("demo-job")
public class ExecController {

    @RequestMapping(value = "exec",method = RequestMethod.PUT)
    public void exec(@RequestBody Task task){
        System.out.println(task);
    }
}
