package cn.edu.bupt.map.service.impl;

import cn.edu.bupt.map.entity.Patroltrack;
import cn.edu.bupt.map.mapper.PatroltrackMapper;
import cn.edu.bupt.map.service.PatroltrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2018/12/19 下午5:25
 */
@Slf4j
@Service
public class PatroltrackImpl implements PatroltrackService {

    @Autowired
    PatroltrackMapper patroltrackMapper;

    @Override
    public void insert(Patroltrack patroltrack) {
        log.trace("Executing insert [{}]");
        patroltrackMapper.insert(patroltrack);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]");
        patroltrackMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Patroltrack findById(Integer id) {
        log.trace("Executing findById [{}]");
        return patroltrackMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Patroltrack> findALl() {
        log.trace("Executing findALl [{}]");
        return patroltrackMapper.selectAll();
    }

    @Override
    public void update(Patroltrack patroltrack) {
        log.trace("Executing update [{}]");
        patroltrackMapper.updateByPrimaryKeyWithBLOBs(patroltrack);
    }
}
