package com.mufiye.service;

import com.mufiye.domain.Entity;

import java.util.List;

public interface EntityService {
    void save(Entity entity);

    void delete(int id);

    void update(Entity entity);

    Entity getById(int id);

    List<Entity> getByType(String type);

    int count();

    List<Entity> getNeighbor(int id);
}
