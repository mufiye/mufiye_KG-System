package com.mufiye.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

// 表示实体的类型，如电梯、电梯组件、故障、解决方案
@Data
@Node("classInfo")
public class ClassInfo {
    @Id
    @GeneratedValue
    private long id;

    //标签的名称，即实体类型
    @Property
    private String className;

    //属性名
    @Property
    private List<String> fieldsName;

    //与属性名一一对应 若不为空则代表下拉框选值,'||'作为分割符
    @Property
    private List<String> fieldsValues;

}
