package cn.pingan.springboottwo.job;

import cn.pingan.springboottwo.quartz.QuartzConfigJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@DisallowConcurrentExecution
@QuartzConfigJob(cronExpression = "0 0/3 * * * ?")
public class TwoJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TwoJob===========start");

        System.out.println("<<<<<<<<<<<>>>>>>>>>");
        System.out.println("<<<<<<<<<<<>>>>>>>>>");

        System.out.println("TwoJob===========end");
    }
}
