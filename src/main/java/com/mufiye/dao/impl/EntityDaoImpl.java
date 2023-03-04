package com.mufiye.dao.impl;

import com.mufiye.dao.EntityDao;
import com.mufiye.domain.Entity;
import com.mufiye.util.JSONUtil;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EntityDaoImpl implements EntityDao {

    @Autowired
    private Driver driver;

    private List<Entity> setDataForEntity(Result result) {
        List<Entity> entities = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        if (result.hasNext()) {//多条结果
            List<Record> records = result.list();
            for (Record recordItem : records) {//一条记录(节点或关系）
                List<Pair<String, Value>> f = recordItem.fields();
                for (Pair<String, Value> pair : f) {
                    String typeName = pair.value().type().name();
                    if ("NODE".equals(typeName)) {
                        Entity entity = new Entity();
                        Node noe4jNode = pair.value().asNode();
                        entity.setId((int) noe4jNode.id());
                        if (!set.add(entity.getId())) {
                            continue;
                        }
                        entity.setType(noe4jNode.labels().iterator().next());
                        entity.setProperties(noe4jNode.asMap());
                        entities.add(entity);
                    } else {
                        System.out.println("relation appears");
                    }
                }
            }
        }
        return entities;
    }

    public Entity getById(int id) {
        String cipher = String.format("MATCH (n) WHERE ID(n) = %d RETURN n", id);
        try (Session session = driver.session()) {
            Result result = session.run(cipher);

            return setDataForEntity(result).get(0);
        }
    }

    @Override
    public List<Entity> getByType(String type) {
        String cipher = String.format("MATCH (n:%s) RETURN n", type);
        try (Session session = driver.session()) {
            Result result = session.run(cipher);
            return setDataForEntity(result);
        }
    }

    @Override
    public List<Entity> getAll(String cypherSql) {

        try (Session session = driver.session()) {

        }
        return null;
    }

    @Override
    public List<Entity> getNeighbor(int id) {
        String cipher = String.format("match (n)-[]-(m) where id(n)=%d return m", id);
        try (Session session = driver.session()) {
            Result result = session.run(cipher);
            List<Entity> entities = setDataForEntity(result);
            entities.add(getById(id));
            return entities;
        }
    }

    public void add(Entity entity) {
        try (Session session = driver.session()) {
            String properties = JSONUtil.getJsonFormat(entity.getProperties());
            String cipher = String.format("CREATE (:%s %s)", entity.getType(), properties); // 对象转为json格式的字符串
            session.run(cipher);
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = driver.session()) {
            String cipher = String.format("MATCH (n) WHERE ID(n) = %d DELETE n", id);
            session.run(cipher);
        }
    }

    @Override
    public void update(Entity entity) {
        try (Session session = driver.session()) {
            Map<String, Object> properties = entity.getProperties();
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

            String cipher = String.format("match (n) where ID(n) = %d set %s", entity.getId(), set);
            session.run(cipher);
        }
    }

    @Override
    public int count() {
        try (Session session = driver.session()) {
            String cipher = "MATCH (n) RETURN COUNT(n) as num";
            Result run = session.run(cipher);
            List<Record> list = run.list();
            Record record = list.get(0);
            List<Pair<String, Value>> fields = record.fields();
            Pair<String, Value> stringValuePair = fields.get(0);
            return stringValuePair.value().asInt();
        }
    }
}
