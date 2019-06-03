package cn.pingan.springboottwo.model.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Blob;

/**
 * <p>
 * 
 * </p>
 *
 * @author GUODONG536
 * @since 2019-04-13
 */
@Data
@Accessors(chain = true)
public class QrtzTriggers {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SCHED_NAME", type = IdType.INPUT)
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
    private Long startTime;
    private Long endTime;
    private String calendarName;
    private Integer misfireInstr;
    private Blob jobData;
}
