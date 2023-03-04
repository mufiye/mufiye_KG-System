package com.mufiye.service.impl;

import com.mufiye.dao.ClassInfoDao;
import com.mufiye.domain.ClassInfo;
import com.mufiye.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {
    @Autowired
    private ClassInfoDao classInfoDao;

    @Override
    public void add(ClassInfo classInfo) {
        classInfoDao.save(classInfo);
    }

    @Override
    public void deleteById(long id) {
        classInfoDao.deleteById( id);
    }

    @Override
    public void updateById(ClassInfo classInfo) {
        classInfoDao.save(classInfo);
    }

    @Override
    public List<ClassInfo> getAll() {

        return classInfoDao.findAll();
    }

    @Override
    public ClassInfo getById(long id) {
        return classInfoDao.findById(id).get();
    }

    @Override
    public int count() {
        return (int) classInfoDao.count();
    }

    @Override
    public ClassInfo getByName(String name) {
        return classInfoDao.findByClassName(name);
    }
}
