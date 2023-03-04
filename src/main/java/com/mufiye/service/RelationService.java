package com.mufiye.service;

import com.mufiye.domain.Relation;

import java.util.List;

public interface RelationService {
    void save(Relation relation);

    void delete(int id);

    void update(Relation relation);

    Relation getById(int id);

    List<Relation> getBySourceId(int sourceId);

    List<Relation> getByType(String startType, String endType);

    List<Relation> getNeighbor(int id);

    int count();

    List<Relation> getByRelationType(String typeName);
}