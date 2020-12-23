package cn.lksun.demojob.demoservice.handle.job;

import cn.lksun.demojob.core.handler.annotation.JobHandle;
import org.springframework.stereotype.Component;

@Component
public class CreateHandle {
    @JobHandle(Name = "CreateHandle")
    public void test(){
        for (int i = 0; i < 3; i++) {
            System.out.println("[CreateHandle] Hello DemoJob!");
        }
    }
}
