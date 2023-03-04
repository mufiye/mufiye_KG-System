package com.mufiye.dao;

import com.mufiye.domain.ClassInfo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassInfoDao extends Neo4jRepository<ClassInfo,Long> {

    ClassInfo findByClassName(String name);
}
