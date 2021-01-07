package cn.lksun.demojob.core.controller;

import cn.lksun.demojob.core.entity.Handle;
import cn.lksun.demojob.core.entity.Task;
import cn.lksun.demojob.core.service.HandleExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("demo-job/exec/")
public class ExecutionController {

    @Resource
    Map<String, Handle> handleMap;

    @RequestMapping(value = "factory",method = RequestMethod.POST)
    public void factory(@RequestBody Task task){
        String handleName = task.handleName;
        Object[] args = task.args.toArray();
        Handle handle = handleMap.get(handleName);
        if (handle != null){
            Object[] parameterTypes = handle.parameterTypes.toArray();
            try {
                HandleExecutionService execService = new HandleExecutionService(handle.className,handle.methodName);
                Object object = execService.getObject();
                Method method = execService.getMethod(handle.methodName, parameterTypes);
                if (method.getParameterCount() != task.args.size()){
                    // 请求参数数量和执行器实际参数数量不匹配
                    throw new Exception("parameterCount Not Match");
                }
                method.invoke(object,args);
                log.info("Successfully");
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                log.error("Handle Error : {}",e.getMessage());
            } catch (Exception e) {
                log.error("Handle Execution : {}",e.getMessage());
            }
        }else {
            log.error("{} Handle not found",handleName);
        }
    }
}
