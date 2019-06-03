package cn.pingan.springboottwo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
@Slf4j
@ComponentScan(basePackages = {"cn.pingan.springboottwo.job"})
public class QuartzConfig {


    @Bean
    public Executor schedulerThreadPool() {
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(25);
        executor.setQueueCapacity(100);
        return executor;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean=new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartzConfig.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public JobFactory cmtsJobFactory() {
        QuartzJobFactory cmtsJobFactory= new QuartzJobFactory();
        return cmtsJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource, JobDetail[] jobDetails, Trigger[] triggers)throws Exception {
        SchedulerFactoryBean factory= new SchedulerFactoryBean();
        factory.setJobFactory(cmtsJobFactory());
        factory.setTaskExecutor(schedulerThreadPool());
        factory.setQuartzProperties(quartzProperties());
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        factory.setSchedulerName("PingAnScheduler");
        factory.setJobDetails(jobDetails);
        factory.setTriggers(triggers);
        factory.setAutoStartup(true);
        return factory;
    }
}
