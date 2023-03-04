package com.mufiye.dao;

import com.mufiye.domain.Relation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationDao {
    Relation getById(int id);
    List<Relation> getBySourceId(int sourceId);
    List<Relation> getByType(String startType, String endType);
    List<Relation> getNeighbor(int id);
    List<Relation> getAll(String cypherSql);
    void add(Relation relation);
    void delete(long id);
    void update(Relation relation);

    int count();

    List<Relation> getByRelationType(String typeName);
}
