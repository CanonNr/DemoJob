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

@Slf4j
@RestController
@RequestMapping("demo-job/exec/")
public class ExecutionController {

    @Resource
    Map<String, Handle> handleMap;



    @RequestMapping(value = "factory",method = RequestMethod.POST)
    public void factory(@RequestBody Task task){
        String handleName = task.handleName;
        List<Object> args = task.args;
        Handle handle = handleMap.get(handleName);
        if (handle != null){
            try {
//                Class<?> clz = Class.forName(handle.className);
//                System.out.println(Arrays.toString(clz.getMethods()));
//                Object object = clz.newInstance();
//                Method method = clz.getMethod(handle.methodName,String.class,String.class);
//                Object invoke = method.invoke(null);
//                System.out.println(invoke);
//                HandleExecutionService execService = new HandleExecutionService(handle.className,handle.methodName);
//                int parameterCount = execService.getMethod().getParameterCount();
//                int size = args.size();
//                if (parameterCount != size){
//                    // 请求参数数量和执行器实际参数数量不匹配
//                    throw new Exception("parameterCount Not Match");
//                }
//                execService.invoke();
                log.info("Successfully");
//            } catch (ClassNotFoundException | NoSuchMethodException e) {
//                log.error("Handle Error : {}",e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
//                log.error("Handle Execution : {}",e.getMessage());
            }
        }else {
            log.error("{} Handle not found",handleName);
        }
    }
}
