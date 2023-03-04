package com.mufiye.dao;

import com.mufiye.domain.RelationInfo;

import java.util.List;

public interface RelationInfoDao {
    void save(RelationInfo relationInfo);

    void deleteById(long id);

    List<RelationInfo> findAll();

    RelationInfo findById(long id);

    int count();

    void update(RelationInfo relationInfo);

    List<RelationInfo> getByStartType(String startType);

    RelationInfo getByName(String name);
}
