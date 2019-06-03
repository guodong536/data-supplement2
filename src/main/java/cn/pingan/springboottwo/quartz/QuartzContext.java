package cn.pingan.springboottwo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class QuartzContext implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    private ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac= applicationContext;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Map<String,Object> taskBeanMap =ac.getBeansWithAnnotation(QuartzConfigJob.class);
        taskBeanMap.keySet().forEach(beanName ->{
            try {
                Class classz = taskBeanMap.get(beanName).getClass();

                QuartzConfigJob jobConfig= (QuartzConfigJob) classz.getAnnotation(QuartzConfigJob.class);
                //任务细节
                BeanDefinitionBuilder jobDetailBuilder= BeanDefinitionBuilder.genericBeanDefinition(JobDetailImpl.class);
                jobDetailBuilder.addPropertyValue("jobClass",classz);
                jobDetailBuilder.addPropertyValue("name",beanName);
                jobDetailBuilder.addPropertyValue("durability",true);
                beanDefinitionRegistry.registerBeanDefinition(beanName,jobDetailBuilder.getBeanDefinition());

                //触发器
                String triggerName= beanName+"Trigger";
                BeanDefinitionBuilder triggerBuilder =BeanDefinitionBuilder.genericBeanDefinition(CronTriggerImpl.class);
                JobKey jobKey= new JobKey(beanName,null);
                TriggerKey triggerKey = new TriggerKey(triggerName,null);
                triggerBuilder.addPropertyValue("jobKey",jobKey);
                triggerBuilder.addPropertyValue("key",triggerKey);
                triggerBuilder.addPropertyValue("cronExpression",jobConfig.cronExpression());
                beanDefinitionRegistry.registerBeanDefinition(triggerName,triggerBuilder.getBeanDefinition());
            } catch (Exception e){
                log.error("exception;"+e.getMessage());
                e.printStackTrace();
            }
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
