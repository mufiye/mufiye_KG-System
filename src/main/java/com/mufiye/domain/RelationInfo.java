package com.mufiye.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

// 表示支持的实体之间的关系
@Data
public class RelationInfo {

    //id
    private long id;

    //关系尾部节点的id
    private int target;

    //关系头部节点的id
    private int source;

    @Property
    private String relationName;

    @Property
    private List<String> fieldsName;

    //与Name对应 若不为空则代表下拉框选值,'||'作为分割符
    @Property
    private List<String> fieldsValues;
}
