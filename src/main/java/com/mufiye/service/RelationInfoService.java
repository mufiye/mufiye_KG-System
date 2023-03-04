package com.mufiye.service;


import com.mufiye.domain.RelationInfo;

import java.util.List;

public interface RelationInfoService {
    void add(RelationInfo relationInfo);

    void deleteById(long id);

    void updateById(RelationInfo relationInfo);

    List<RelationInfo> getAll();

    RelationInfo getById(long id);

    List<RelationInfo> getByStartType(String startType);

    int count();

    RelationInfo getByName(String name);
}
