package cn.lksun.demojob.core.annotation;

import cn.lksun.demojob.core.bean.HandleConfig;
import cn.lksun.demojob.core.controller.HandleController;
import cn.lksun.demojob.core.handler.aspect.ScannerComponent;
import cn.lksun.demojob.core.service.Register;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ScannerComponent.class, HandleConfig.class, HandleController.class, Register.class})
@Inherited
public @interface EnableDemoJob {
}
