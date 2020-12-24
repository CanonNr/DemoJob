package cn.lksun.demojob.demoservice.handle.job;

import cn.lksun.demojob.core.handler.annotation.JobHandle;
import org.springframework.stereotype.Component;

@Component
public class CreateHandle {
    @JobHandle(Name = "CreateHandle",Description = "测试执行器")
    public void test(String id,Integer time){
        for (int i = 0; i < 3; i++) {
            System.out.println("[CreateHandle] Hello DemoJob!");
        }
    }
}
