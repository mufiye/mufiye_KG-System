package com.mufiye.domain;

import lombok.Data;

import java.util.Map;

// 表示具体的实体间关系
@Data
public class Relation {

    //id
    private long id;

    //关系尾部节点的id
    private int target;

    //关系头部节点的id
    private int source;

    //标签种类
    private String type;

    //储存属性键值对
    private Map<String,Object> properties;

}
