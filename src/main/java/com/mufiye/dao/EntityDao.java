package com.mufiye.dao;

import com.mufiye.domain.Entity;

import java.util.List;

public interface EntityDao  {
    Entity getById(int id);
    /**
     * 获取一个类型的节点
     * @param type 类型，即标签
     * @return
     */
    List<Entity> getByType(String type);
    List<Entity> getAll(String cypherSql);
    List<Entity> getNeighbor(int id);
    void add(Entity entity);
    void delete(long id);
    void update(Entity entity);

    int count();
}
