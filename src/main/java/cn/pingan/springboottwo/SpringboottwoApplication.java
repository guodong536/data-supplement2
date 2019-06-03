package cn.pingan.springboottwo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan({"cn.pingan.springboottwo.mapper"})
@ServletComponentScan
@EnableAsync
@EnableApolloConfig
public class SpringboottwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringboottwoApplication.class, args);
    }

}
