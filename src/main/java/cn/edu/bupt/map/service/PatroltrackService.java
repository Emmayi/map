package cn.edu.bupt.map.service;

import cn.edu.bupt.map.entity.Patroltrack;

public interface PatroltrackService {

    public void insert(Patroltrack patroltrack);

    public void deleteById(Integer id);

    Patroltrack findById(Integer id);

}
