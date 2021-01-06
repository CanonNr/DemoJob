package cn.lksun.demojob.admin.controller;

import cn.lksun.demojob.admin.constant.RedisTableName;
import cn.lksun.demojob.admin.entity.Task;
import cn.lksun.demojob.admin.service.node.NodeService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("demo-job")
public class ExecController {

    @Resource
    NodeService nodeService;

    @Resource
    RedisTemplate<String, Serializable> redisTemplate;

    StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "exec",method = RequestMethod.PUT)
    public void exec(@RequestBody Task task){
        String taskId = UUID.randomUUID().toString().replace("-","").toLowerCase();
        // 存入时间set表
        BoundZSetOperations<String, Serializable> timeOps = redisTemplate.boundZSetOps(RedisTableName.REDIS_TIME_RANK_TABLE_NAME);
        timeOps.add(taskId, task.time);
        // 存入任务hash表
        BoundHashOperations<String, Object, Object> taskOps = redisTemplate.boundHashOps(RedisTableName.REDIS_TASK_INFO_TABLE_NAME);
        String taskJson = JSON.toJSON(task).toString();
        taskOps.put(taskId,taskJson);
        log.info("Task Create Success - TaskId:{}",taskId);
    }
}
