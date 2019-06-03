package cn.pingan.springboottwo.common;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelExcel {


    /**
     * 姓名
     * @return
     */
    String name() default "";

    /**
     * 性别
     * @return
     */
    String sex() default "";

    /**
     * 年龄
     * @return
     */
    int age() default 18;

    /**
     * 电话
     */
    int sortNum() default 0;
}
