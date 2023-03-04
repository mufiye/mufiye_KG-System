package com.mufiye.service.impl;

import com.mufiye.dao.EntityDao;
import com.mufiye.domain.Entity;
import com.mufiye.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    EntityDao entityDao;

    @Override
    public void save(Entity entity) {
        entityDao.add(entity);
    }

    @Override
    public void delete(int id) {
        entityDao.delete(id);
    }

    @Override
    public void update(Entity entity) {
        entityDao.update(entity);
    }

    @Override
    public Entity getById(int id) {
        return entityDao.getById(id);
    }

    @Override
    public List<Entity> getByType(String type) {
        return entityDao.getByType(type);
    }

    @Override
    public int count() {
        return entityDao.count();
    }

    @Override
    public List<Entity> getNeighbor(int id) {
        return entityDao.getNeighbor(id);
    }
}
