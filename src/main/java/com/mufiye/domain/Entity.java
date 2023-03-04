package com.mufiye.domain;

import lombok.Data;

import java.util.Map;

// 表示具体的实体
@Data
public class Entity {

    //id
    private int id;

    //标签 即实体类型
    private String type;

    //储存属性键值对
    private Map<String,Object> properties;
}
