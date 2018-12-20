package cn.edu.bupt.map.service.impl;

import cn.edu.bupt.map.entity.Warning;
import cn.edu.bupt.map.mapper.WarningMapper;
import cn.edu.bupt.map.service.WarningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2018/12/19 下午8:20
 */

@Slf4j
@Service
public class WarningImpl implements WarningService {

    @Autowired
    WarningMapper warningMapper;

    @Override
    public void insert(Warning warning) {
        log.trace("Executing insert [{}]");
        warningMapper.insert(warning);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]");
        warningMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Warning findById(Integer id) {
        log.trace("Executing findById [{}]");
        return warningMapper.selectByPrimaryKey(id);
    }
}
