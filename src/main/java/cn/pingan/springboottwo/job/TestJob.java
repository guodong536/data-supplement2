package cn.pingan.springboottwo.job;

import cn.pingan.springboottwo.quartz.QuartzConfigJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
@Slf4j
@QuartzConfigJob(cronExpression = "0/3 * * * * ?")
public class TestJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("TestJob===========start");

        System.out.println("============+++++++=======");

        System.out.println("TestJob===========end");
    }
}
