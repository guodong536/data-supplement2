package cn.pingan.springboottwo.quartz;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface QuartzConfigJob {

    String cronExpression() default "";
}
