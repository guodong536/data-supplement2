package cn.pingan.springboottwo.common.importConfig;

import java.lang.annotation.*;

@Documented
@Target(value={ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PingAn {

    int age() default 22;

    String name() default "";

    String sex() default "0";
}
