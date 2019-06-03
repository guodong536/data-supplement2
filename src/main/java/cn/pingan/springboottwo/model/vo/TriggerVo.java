package cn.pingan.springboottwo.model.vo;

import lombok.Data;

@Data
public class TriggerVo {

    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;
    private String description;
    private Long nextFireTime;
    private Long prevFireTime;
    private Integer priority;
    private String triggerState;
    private String triggerType;
    private String cron;
}
