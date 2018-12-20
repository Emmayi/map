package cn.edu.bupt.map.service.impl;

import cn.edu.bupt.map.entity.Track;
import cn.edu.bupt.map.mapper.TrackMapper;
import cn.edu.bupt.map.service.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2018/12/19 下午8:13
 */

@Slf4j
@Service
public class TrackImpl implements TrackService {

    @Autowired
    TrackMapper trackMapper;

    @Override
    public void insert(Track track) {
        log.trace("Executing insert [{}]");
        trackMapper.insert(track);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]");
        trackMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Track findById(Integer id) {
        log.trace("Executing findById [{}]");
        return trackMapper.selectByPrimaryKey(id);
    }
}
