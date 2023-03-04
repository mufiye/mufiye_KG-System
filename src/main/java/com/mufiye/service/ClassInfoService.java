package com.mufiye.service;


import com.mufiye.domain.ClassInfo;

import java.util.List;

public interface ClassInfoService {
    void add(ClassInfo classInfo);

    void deleteById(long id);

    void updateById(ClassInfo classInfo);

    List<ClassInfo> getAll();

    ClassInfo getById(long id);

    int count();

    ClassInfo getByName(String name);
}
