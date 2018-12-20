package cn.edu.bupt.map.service.impl;

import cn.edu.bupt.map.entity.Pipe;
import cn.edu.bupt.map.mapper.PipeMapper;
import cn.edu.bupt.map.service.PipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2018/12/19 下午5:56
 */

@Slf4j
@Service
public class PipeImpl implements PipeService {

    @Autowired
    PipeMapper pipeMapper;

    @Override
    public void insert(Pipe pipe) {
        log.trace("Executing insert [{}]");
        pipeMapper.insert(pipe);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]");
        pipeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Pipe findById(Integer id) {
        log.trace("Executing findById [{}]");
        return pipeMapper.selectByPrimaryKey(id);
    }
}
