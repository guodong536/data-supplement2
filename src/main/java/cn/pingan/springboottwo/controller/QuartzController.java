package cn.pingan.springboottwo.controller;

import cn.pingan.springboottwo.model.vo.ConfigVo;
import cn.pingan.springboottwo.model.vo.TriggerVo;
import cn.pingan.springboottwo.service.inter.QrtzTriggersService;
import cn.pingan.springboottwo.utils.CronUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/quartz_job")
@Api(description = "定时任务")
@Slf4j
@RestController
public class QuartzController extends  BaseController{

    @Autowired
    private QrtzTriggersService qrtzTriggersService;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Resource(name = "schedulerFactoryBean")
    private Scheduler scheduler;

    //查询所有的定时任务-1
    @RequestMapping(value = "getAllJobList",method = RequestMethod.POST)
    public Map<String,Object> getAllJobList(@RequestBody ConfigVo configVo){
        Map<String,Object> result =new HashMap<>();

        try {
            List<TriggerVo> triggerVoList =qrtzTriggersService.getAllTrigger();
            result.put("resList",triggerVoList);
            result.put("code","0");
            result.put("msg","success");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    //开启所有的定时任务-2
    @RequestMapping(value = "openAllJobs",method = RequestMethod.POST)
    public Map<String,Object> openAllJobs(@RequestBody ConfigVo configVo){
        Map<String,Object> result =new HashMap<>();

        try {
            scheduler.start();
            result.put("ret",1);
            result.put("msg","成功开启所有定时任务！！");
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    //关闭所有的定时任务-3
    @RequestMapping(value = "shutdownAllJobs",method = RequestMethod.POST)
    public Map<String,Object> shutdownAllJobs(@RequestBody ConfigVo configVo){
        Map<String,Object> result =new HashMap<>();

        try {
            if(!scheduler.isShutdown()){
                scheduler.standby();
            }
            result.put("ret",1);
            result.put("msg","成功关闭所有定时任务！！");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    //触发一次定时任务-4
    @RequestMapping(value = "checkJob",method = RequestMethod.POST)
    public Map<String,Object> checkJob(@RequestBody TriggerVo triggerVo){
        Map<String,Object> result =new HashMap<>();

        try {
            scheduler.triggerJob(JobKey.jobKey(triggerVo.getJobName(),triggerVo.getJobGroup()));
            result.put("ret",0);
            result.put("msg","成功触发定时任务");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    //添加新的定时任务
    @RequestMapping(value = "addJob",method = RequestMethod.POST)
    public Map<String,Object> addJob(@RequestBody ConfigVo configVo){
        Map<String,Object> result =new HashMap<>();

        try {

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    //修改定时任务
    @RequestMapping(value = "updateJob",method = RequestMethod.POST)
    public Map<String,Object> updateJob(@RequestBody ConfigVo configVo){
        Map<String,Object> result =new HashMap<>();

        try {

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }



    //移除一个定时任务-5
    @RequestMapping(value = "closeOneJob",method = RequestMethod.POST)
    public Map<String,Object> closeOneJob(@RequestBody TriggerVo triggerVo){
        Map<String,Object> result =new HashMap<>();

        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerVo.getTriggerName(),triggerVo.getTriggerGroup());
            scheduler.pauseTrigger(triggerKey);
            result.put("ret",0);
            result.put("msg","关闭某一个定时任务");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    //开启一个定时任务-6
    @RequestMapping(value = "startOneJob",method = RequestMethod.POST)
    public Map<String,Object> startOneJob(@RequestBody TriggerVo triggerVo){
        Map<String,Object> result =new HashMap<>();

        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerVo.getTriggerName(),triggerVo.getTriggerGroup());
            scheduler.pauseTrigger(triggerKey);
            scheduler.resumeJob(JobKey.jobKey(triggerVo.getJobName(),triggerVo.getJobGroup()));
            result.put("ret",0);
            result.put("msg","开启某一个定时任务");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    //删除一个定时任务-7
    @RequestMapping(value = "deleteOneJob",method = RequestMethod.POST)
    public Map<String,Object> deleteOneJob(@RequestBody TriggerVo triggerVo){
        Map<String,Object> result =new HashMap<>();

        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerVo.getTriggerName(),triggerVo.getTriggerGroup());
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(triggerVo.getJobName(),triggerVo.getJobGroup()));
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * 功能：修改一个任务的触发时间-8
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     *            触发器名
     * @param triggerGroupName
     *            触发器组名
     * @param cron
     *            时间设置，参考quartz说明文档
     */
    @PostMapping(value="/modify_job")
    public  Map<String,Object> modifyJobTime(@RequestBody TriggerVo triggerVo) {

        Map<String,Object> result =new HashMap<>();

        String jobName = triggerVo.getJobName();
        String jobGroupName =triggerVo.getTriggerGroup();
        String triggerName = triggerVo.getTriggerName();
        String triggerGroupName=triggerVo.getTriggerGroup();
        String cron =triggerVo.getCron();
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                result.put("ret",-1);
                result.put("msg","修改失败！");
                return result;
            }

            //判断新的cron表达式是否符合要求
            boolean flag=CronUtils.checkCron(cron);
            if(!flag){
                result.put("ret",-1);
                result.put("msg","cron表达式不符合要求");
                return result;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        result.put("ret",1);
        result.put("msg","修改定时任务成功！");
        return result;
    }
}
