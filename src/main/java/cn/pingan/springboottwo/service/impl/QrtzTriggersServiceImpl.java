package cn.pingan.springboottwo.service.impl;

import cn.pingan.springboottwo.mapper.QrtzTriggersMapper;
import cn.pingan.springboottwo.model.po.QrtzTriggers;
import cn.pingan.springboottwo.model.vo.TriggerVo;
import cn.pingan.springboottwo.service.inter.QrtzTriggersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GUODONG536
 * @since 2019-04-13
 */
@Service
public class QrtzTriggersServiceImpl extends ServiceImpl<QrtzTriggersMapper, QrtzTriggers> implements QrtzTriggersService {

    @Autowired
    private QrtzTriggersMapper qrtzTriggersMapper;

    @Override
    public List<TriggerVo> getAllTrigger() {
        return qrtzTriggersMapper.getAllTrigger();
    }
}
