package com.mufiye.dao.impl;

import com.mufiye.dao.RelationDao;
import com.mufiye.domain.Relation;
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
public class RelationDaoImpl implements RelationDao {

    @Autowired
    private Driver driver;

    private List<Relation> setDataForRelation(Result result) {
        List<Relation> relations = new ArrayList<>();
        if (result.hasNext()) {//多条结果
            List<Record> records = result.list();
            for (Record recordItem : records) {//一条记录(节点或关系）
                List<Pair<String, Value>> f = recordItem.fields();
                for (Pair<String, Value> pair : f) {
                    String typeName = pair.value().type().name();
                    if ("RELATIONSHIP".equals(typeName)) {
                        Relation relation = new Relation();
                        Relationship rship = pair.value().asRelationship();
                        relation.setId(rship.id());
                        relation.setSource((int) rship.startNodeId());
                        relation.setTarget((int) rship.endNodeId());
                        relation.setType(rship.type());
                        relation.setProperties(rship.asMap());

                        relations.add(relation);
                    }
                }
            }
        }
        return relations;
    }

    @Override
    public Relation getById(int id) {
        String cipher = String.format("MATCH ()-[m]->() WHERE ID(m) = %d RETURN m", id);
        try (Session session = driver.session()) {
            Result result = session.run(cipher);

            return setDataForRelation(result).get(0);
        }
    }

    @Override
    public List<Relation> getBySourceId(int sourceId) {
        String cipher = String.format("MATCH (n)-[m]->() WHERE ID(n) = %d RETURN m", sourceId);
        try (Session session = driver.session()) {
            Result result = session.run(cipher);
            return setDataForRelation(result);
        }
    }

    @Override
    public List<Relation> getByType(String startType, String endType) {
        String cipher = String.format("MATCH (:%s)-[m]->(:%s) RETURN m", startType, endType);
        try (Session session = driver.session()) {
            Result result = session.run(cipher);
            return setDataForRelation(result);
        }
    }

    @Override
    public List<Relation> getNeighbor(int id) {
        String cipher = String.format("match (n)-[l]-() where id(n)=%d return l", id);
        try (Session session = driver.session()) {
            Result result = session.run(cipher);
            return setDataForRelation(result);
        }
    }

    @Override
    public List<Relation> getAll(String cypherSql) {
        return null;
    }

    @Override
    public void add(Relation relation) {
        try (Session session = driver.session()) {

            String properties = JSONUtil.getJsonFormat(relation.getProperties());
            String cipher = String.format("match (n),(m)\n" +
                                              "where ID(n) = %d and ID(m) = %d\n" +
                                              "create (n)-[:%s %s]->(m)", relation.getSource(), relation.getTarget(),
                                          relation.getType(), properties);
            session.run(cipher);
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = driver.session()) {
            String cipher = String.format("match ()-[n]->() where ID(n) = %d delete n", id);
            session.run(cipher);
        }
    }

    @Override
    public void update(Relation relation) {
        try (Session session = driver.session()) {
            Map<String, Object> properties = relation.getProperties();
            if (properties.entrySet().size() == 0) {
                return;
            }
            StringBuilder set = new StringBuilder();
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                if (set.length() > 0) {
                    set.append(",");
                }
                set.append("n.").append(entry.getKey()).append(" = '").append(entry.getValue()).append("' ");
            }

            String cipher = String.format("match ()-[n]-() where ID(n) = %d set %s", relation.getId(), set);
            session.run(cipher);
        }

    }

    @Override
    public int count() {
        try (Session session = driver.session()) {

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
    public List<Relation> getByRelationType(String typeName) {
        String cipher = String.format("MATCH ()-[m:%s]->() RETURN m", typeName);
        try (Session session = driver.session()) {
            Result result = session.run(cipher);
            return setDataForRelation(result);
        }
    }
}
