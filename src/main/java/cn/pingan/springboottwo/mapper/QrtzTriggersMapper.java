package cn.pingan.springboottwo.mapper;

import cn.pingan.springboottwo.model.po.QrtzTriggers;
import cn.pingan.springboottwo.model.vo.TriggerVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GUODONG536
 * @since 2019-04-13
 */
public interface QrtzTriggersMapper extends BaseMapper<QrtzTriggers> {

    //查询所有的定时任务
    List<TriggerVo> getAllTrigger();
}
