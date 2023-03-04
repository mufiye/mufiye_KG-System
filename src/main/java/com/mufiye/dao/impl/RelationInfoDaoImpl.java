package com.mufiye.dao.impl;

import com.mufiye.dao.RelationInfoDao;
import com.mufiye.domain.RelationInfo;
import com.mufiye.util.JSONUtil;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Relationship;
import org.neo4j.driver.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RelationInfoDaoImpl implements RelationInfoDao {

    @Autowired
    private Driver driver;


    private List<RelationInfo> setDataForRelationInfo(Result result) {
        List<RelationInfo> relationInfos = new ArrayList<>();
        if (result.hasNext()) {//多条结果
            List<Record> records = result.list();
            for (Record recordItem : records) {//一条记录(节点或关系）
                List<Pair<String, Value>> f = recordItem.fields();
                for (Pair<String, Value> pair : f) {
                    String typeName = pair.value().type().name();
                    if ("RELATIONSHIP".equals(typeName)) {
                        RelationInfo relationInfo = new RelationInfo();
                        Relationship rship = pair.value().asRelationship();
                        relationInfo.setId(rship.id());
                        relationInfo.setSource((int) rship.startNodeId());
                        relationInfo.setTarget((int) rship.endNodeId());
                        Map<String, Object> map = rship.asMap();
                        relationInfo.setFieldsName((List<String>) map.get("fieldsName"));
                        relationInfo.setFieldsValues((List<String>) map.get("fieldsValues"));
                        relationInfo.setRelationName((String) map.get("relationName"));
                        relationInfos.add(relationInfo);
                    }
                }
            }
        }
        return relationInfos;
    }


    @Override
    public void save(RelationInfo relationInfo) {
        try(Session session = driver.session()) {

            String names = JSONUtil.getJsonFormat(relationInfo.getFieldsName());
            String values = JSONUtil.getJsonFormat(relationInfo.getFieldsValues());
            String cipher = String.format("match (n),(m)\n" +
                    "where ID(n) = %d and ID(m) = %d\n" +
                    "create (n)-[:relationInfo {relationName:'%s',fieldsName:%s,fieldsValues:%s}]->(m)",relationInfo.getSource(),relationInfo.getTarget()
                    ,relationInfo.getRelationName(),names,values);
            session.run(cipher);
        }
    }

    @Override
    public void deleteById(long id) {
        try(Session session = driver.session()) {
            String cipher = String.format("match ()-[n:relationInfo]->() where ID(n) = %d delete n",id);
            session.run(cipher);
        }
    }

    @Override
    public List<RelationInfo> findAll() {
        String cipher = "MATCH ()-[m:relationInfo]->() RETURN m";
        try(Session session = driver.session()) {
            Result result = session.run(cipher);

            return setDataForRelationInfo(result);
        }
    }

    @Override
    public RelationInfo findById(long id) {
        String cipher = String.format("MATCH ()-[m]->() WHERE ID(m) = %d RETURN m",id);
        try(Session session = driver.session()) {
            Result result = session.run(cipher);

            return setDataForRelationInfo(result).get(0);
        }
    }

    @Override
    public int count() {
        try(Session session = driver.session()) {

            String cipher = "match ()-[n]->() return count(n)";
            Result run = session.run(cipher);
            List<Record> list = run.list();
            Record record = list.get(0);
            List<Pair<String, Value>> fields = record.fields();
            Pair<String, Value> stringValuePair = fields.get(0);
            return stringValuePair.value().asInt();
        }
    }

    @Override
    public void update(RelationInfo relationInfo) {
        deleteById(relationInfo.getId());
        save(relationInfo);
    }

    @Override
    public List<RelationInfo> getByStartType(String startType) {
        String cipher = String.format("MATCH (n:classInfo)-[m:relationInfo]->() where n.className='%s' RETURN m",startType);
        try(Session session = driver.session()) {
            Result result = session.run(cipher);

            return setDataForRelationInfo(result);
        }
    }

    @Override
    public RelationInfo getByName(String name) {
        String cipher = String.format("MATCH ()-[m]->() WHERE m.relationName='%s' RETURN m",name);
        try(Session session = driver.session()) {
            Result result = session.run(cipher);

            return setDataForRelationInfo(result).get(0);
        }
    }
}
