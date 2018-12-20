package cn.edu.bupt.map.service;

import cn.edu.bupt.map.entity.Warning;

public interface WarningService {

    public void insert(Warning warning);

    public void deleteById(Integer id);

    Warning findById(Integer id);

}
