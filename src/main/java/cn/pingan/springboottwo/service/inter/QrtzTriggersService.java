package cn.pingan.springboottwo.service.inter;

import cn.pingan.springboottwo.model.po.QrtzTriggers;
import cn.pingan.springboottwo.model.vo.TriggerVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GUODONG536
 * @since 2019-04-13
 */
public interface QrtzTriggersService extends IService<QrtzTriggers> {

    //查询所有的定时任务
    List<TriggerVo> getAllTrigger();
}
