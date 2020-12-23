package cn.lksun.demojob.demoservice.handle.job;

import cn.lksun.demojob.core.handler.annotation.JobHandle;
import org.springframework.stereotype.Component;


@Component
public class PrintHandle {

    @JobHandle(Name = "PrintHandle")
    public void test(){
        for (int i = 0; i < 3; i++) {
            System.out.println("[PrintHandle] Hello DemoJob!");
        }
    }
}
