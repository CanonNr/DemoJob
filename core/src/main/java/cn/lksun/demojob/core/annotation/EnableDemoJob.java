package cn.lksun.demojob.core.annotation;

import cn.lksun.demojob.core.bean.HandleConfig;
import cn.lksun.demojob.core.controller.ExecutionController;
import cn.lksun.demojob.core.controller.HandleController;
import cn.lksun.demojob.core.controller.HeartbeatController;
import cn.lksun.demojob.core.controller.InfoController;
import cn.lksun.demojob.core.handler.aspect.ScannerComponent;
import cn.lksun.demojob.core.task.RegisterTask;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({
    ScannerComponent.class,
    HandleConfig.class,
    RegisterTask.class,
    HandleController.class,
    InfoController.class,
    HeartbeatController.class,
    ExecutionController.class,
})
@ConfigurationPropertiesScan("cn.lksun.demojob.core.config")
@Inherited
public @interface EnableDemoJob {
}
