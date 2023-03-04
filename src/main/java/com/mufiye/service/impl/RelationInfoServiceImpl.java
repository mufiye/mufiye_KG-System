package com.mufiye.service.impl;

import com.mufiye.dao.RelationInfoDao;
import com.mufiye.domain.RelationInfo;
import com.mufiye.service.RelationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationInfoServiceImpl implements RelationInfoService {
    @Autowired
    private RelationInfoDao relationInfoDao;

    @Override
    public void add(RelationInfo relationInfo) {
        relationInfoDao.save(relationInfo);
    }

    @Override
    public void deleteById(long id) {
        relationInfoDao.deleteById( id);
    }

    @Override
    public void updateById(RelationInfo relationInfo) {
        relationInfoDao.update(relationInfo);
    }

    @Override
    public List<RelationInfo> getAll() {
        return relationInfoDao.findAll();
    }

    @Override
    public RelationInfo getById(long id) {
        return relationInfoDao.findById(id);
    }

    @Override
    public List<RelationInfo> getByStartType(String startType) {
        return relationInfoDao.getByStartType(startType);
    }

    @Override
    public int count() {
        return relationInfoDao.count();
    }

    @Override
    public RelationInfo getByName(String name) {
        return relationInfoDao.getByName(name);
    }
}
