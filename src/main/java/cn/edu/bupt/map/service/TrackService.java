package cn.edu.bupt.map.service;

import cn.edu.bupt.map.entity.Patroltrack;
import cn.edu.bupt.map.entity.Track;

public interface TrackService {

    public void insert(Track track);

    public void deleteById(Integer id);

    Track findById(Integer id);
}
