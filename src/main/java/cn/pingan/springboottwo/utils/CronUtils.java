package cn.pingan.springboottwo.utils;

import org.quartz.impl.triggers.CronTriggerImpl;

import java.util.Date;

public class CronUtils {

    public static boolean checkCron(String cron){
        try{
            CronTriggerImpl cronTrigger=new CronTriggerImpl();
            cronTrigger.setCronExpression(cron);
            Date date = cronTrigger.computeFirstFireTime(null);
            if(date ==null){
                return false;
            }
            System.out.println(date);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
