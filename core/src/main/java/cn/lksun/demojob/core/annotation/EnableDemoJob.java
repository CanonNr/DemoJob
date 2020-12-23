package cn.lksun.demojob.core.annotation;

import cn.lksun.demojob.core.handler.aspect.ScannerComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ScannerComponent.class})
@Inherited
public @interface EnableDemoJob {
}
