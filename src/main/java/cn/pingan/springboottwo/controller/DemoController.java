package cn.pingan.springboottwo.controller;

import cn.pingan.springboottwo.job.TestJob;
import cn.pingan.springboottwo.quartz.QuartzConfigJob;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
@RestController
@RequestMapping("/demo")
@Api(description = "apollo测试")
public class DemoController {

    public static void main(String[] args) {

        Field[] fields = TestJob.class.getFields();
        for (Field f:fields) {
            System.out.println(f);
            System.out.println("="+f.getName());
        }

        QuartzConfigJob configJob= TestJob.class.getAnnotation(QuartzConfigJob.class);
        System.out.println(configJob.cronExpression());

        Class c= configJob.getClass();
        System.out.println(c);
    }


    @Value("${hello:apollo}")
    private String hello;

    @GetMapping("/helloApollo")
    public String sayApollo(){
        return "hello "+hello;
    }


}
