package cn.edu.bupt.map.service;

import cn.edu.bupt.map.entity.Pipe;

public interface PipeService {

    public void insert(Pipe pipe);

    public void deleteById(Integer id);

    Pipe findById(Integer id);
}
