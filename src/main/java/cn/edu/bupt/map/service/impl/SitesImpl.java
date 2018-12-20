package cn.edu.bupt.map.service.impl;

import cn.edu.bupt.map.entity.Sites;
import cn.edu.bupt.map.mapper.SitesMapper;
import cn.edu.bupt.map.service.SitesSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2018/12/19 下午6:02
 */

@Service
@Slf4j
public class SitesImpl implements SitesSevice {

    @Autowired
    SitesMapper sitesMapper;

    @Override
    public void insert(Sites sites) {
        log.trace("Executing insert [{}]");
        sitesMapper.insert(sites);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]");
        sitesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Sites findById(Integer id) {
        log.trace("Executing findById [{}]");
        return sitesMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Sites sites) {
        log.trace("Executing update [{}]");
        sitesMapper.updateByPrimaryKey(sites);
    }
}
