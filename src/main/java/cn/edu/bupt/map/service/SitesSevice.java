package cn.edu.bupt.map.service;

import cn.edu.bupt.map.entity.Sites;

public interface SitesSevice {

    public void insert(Sites sites);

    public void deleteById(Integer id);

    Sites findById(Integer id);

    public void update(Sites sites);
}
