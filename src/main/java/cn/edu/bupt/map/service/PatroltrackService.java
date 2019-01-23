package cn.edu.bupt.map.service;

import cn.edu.bupt.map.entity.Patroltrack;

import java.util.List;

public interface PatroltrackService {

    public void insert(Patroltrack patroltrack);

    public void deleteById(Integer id);

    Patroltrack findById(Integer id);

    List<Patroltrack> findALl();

    public void update(Patroltrack patroltrack);

}
