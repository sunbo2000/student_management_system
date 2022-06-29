package org.snbo.ssms.service.impl;

import org.snbo.ssms.bean.BaseBean;
import org.snbo.ssms.mapper.BaseMapper;
import org.snbo.ssms.service.BaseService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;


/**
 * @author sunbo
 * @date 2022-06-07-21:53
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseBean> implements BaseService<T> {

    protected M baseMapper;

    public BaseServiceImpl(M baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public boolean save(T t) {
        return baseMapper.saveInfo(t);
    }

    @Override
    public boolean saveAll(List<T> tList) {
        return baseMapper.saveAllInfo(tList);
    }

    @Override
    public boolean remove(T t) {
        return baseMapper.removeInfo(t);
    }

    @Override
    public boolean removeAll(List<T> tList) {
        return baseMapper.removeAllInfo(tList);
    }

    @Override
    public boolean update(T t) {
        return baseMapper.updateInfo(t);
    }

    @Override
    public T getById(Integer id) {
        return baseMapper.getInfoById(id);
    }

    @Override
    public List<T> getPage(Integer current, Integer size, Comparator<T> comparator) {
        return baseMapper.getInfoPage(current, size, comparator);
    }

    @Override
    public List<T> getAllOfList() {
        return baseMapper.getAllInfoOfList();
    }

    @Override
    public Map<Integer, T> getAllOfMap() {
        return baseMapper.getAllInfoOfMap();
    }

}

