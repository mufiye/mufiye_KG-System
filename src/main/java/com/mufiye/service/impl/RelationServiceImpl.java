package com.mufiye.service.impl;

import com.mufiye.dao.RelationDao;
import com.mufiye.domain.Relation;
import com.mufiye.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationDao relationDao;

    @Override
    public void save(Relation relation) {
        relationDao.add(relation);
    }

    @Override
    public void delete(int id) {
        relationDao.delete(id);
    }

    @Override
    public void update(Relation relation) {
        relationDao.update(relation);
    }

    @Override
    public Relation getById(int id) {
        return relationDao.getById(id);
    }

    @Override
    public List<Relation> getBySourceId(int sourceId) {
        return relationDao.getBySourceId(sourceId);
    }

    @Override
    public List<Relation> getByType(String startType, String endType) {
        return relationDao.getByType(startType, endType);
    }

    @Override
    public List<Relation> getNeighbor(int id) {
        return relationDao.getNeighbor(id);
    }

    @Override
    public int count() {
        return relationDao.count();
    }

    @Override
    public List<Relation> getByRelationType(String typeName) {
        return relationDao.getByRelationType(typeName);
    }
}
