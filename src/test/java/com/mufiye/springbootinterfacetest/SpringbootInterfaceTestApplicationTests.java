package com.mufiye.springbootinterfacetest;

import com.mufiye.config.BeanUtils;
import com.mufiye.dao.ClassInfoDao;
import com.mufiye.dao.RelationDao;
import com.mufiye.dao.EntityDao;
import com.mufiye.dao.RelationInfoDao;
import com.mufiye.domain.*;
import com.mufiye.service.UserService;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class SpringbootInterfaceTestApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    ClassInfoDao classInfoDao;
    @Autowired
    EntityDao entityDao;

    @Autowired
    RelationDao relationDao;
    @Autowired
    RelationInfoDao relationInfoDao;

    @Value("${server.port}")
    String value;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("yemengfei_test");
        user.setPassword("123456");
        user.setName("mufiye_test");
        user.setEmail("mufiye140520@gmail.com");

        System.out.println(userService.register(user));
    }

    @Test
    void testLogin() {
        String username = "mufiye";
        String password = "123456";
        System.out.println(userService.login(username, password));
    }

    @Test
    void testUpdate() {

        User user = new User();
        user.setId(88L);
        user.setUsername("ADMIN2131");
        user.setPassword("222");
        user.setName("测试");
        user.setEmail("1433137434@qq.com");

        System.out.println(userService.updateById(user));
    }

    @Test
    void testGetByID() {
        User byId = userService.getById(108);
        System.out.println(byId);
    }

    @Test
    void testDelete() {
        System.out.println(userService.removeById(108));
    }

    @Test
    void testGetAll() {
        System.out.println(userService.list());
    }


    @Test
    void testDaoDelete(){
        classInfoDao.deleteById(113L);
        classInfoDao.deleteById(115L);
    }

    @Test
    void testDaoSelect(){
        for (ClassInfo classInfo : classInfoDao.findAll()) {
            System.out.println(classInfo);
        }

    }


    @Test
    void testEntity(){
        System.out.println(value);
        System.out.println(BeanUtils.getBean(Driver.class));
    }

    @Test
    void testEntity2() throws Exception {
        entityDao.delete(113);
        entityDao.delete(91);
    }

    @Test
    void testEntity3() throws Exception {
        Entity byId = entityDao.getById(91);
        System.out.println(byId);
    }

    @Test
    void testEntityGetType() throws Exception {
        List<Entity> byId = entityDao.getByType("故障现象");
        System.out.println(byId);
    }

    @Test
    void testEntityAdd() throws Exception {
        Entity entity = new Entity();
        entity.setType("故障现象");
        Map<String, Object> map = new HashMap<>();
        map.put("phenomenonId",3);
        map.put("phenomenonName","意外移动");
        entity.setProperties(map);
        entityDao.add(entity);
    }

    @Test
    void testCount(){
        entityDao.count();
    }

    @Test()
    void testRelationAdd(){
        Relation relation = new Relation();
        relation.setSource(300);
        relation.setTarget(295);
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("测试1","2111");

        relation.setProperties(objectObjectHashMap);
        relation.setType("指标状态");
        relationDao.add(relation);
    }


    @Test
    void countRelation(){
        System.out.println(relationDao.count());
    }

    @Test
    void testRelationDelete(){
        relationDao.delete(1);
    }

    @Test
    void testRelationUpdate(){
        Relation relation = new Relation();
        relation.setId(3L);
        relation.setSource(87);
        relation.setTarget(75);
        relation.setType("OnlyCanBeFriend");
        relationDao.update(relation);
    }

    @Test
    void testGetByType(){
        List<Relation> byType = relationDao.getByType("classInfo", "classInfo");
        System.out.println(byType);
    }

    @Test
    void testRelationInfoAdd(){
        RelationInfo relationInfo = new RelationInfo();
        relationInfo.setSource(254);
        relationInfo.setTarget(234);
        relationInfo.setFieldsName(Arrays.asList("测试1","测试2"));
        relationInfo.setFieldsValues(Arrays.asList("",""));
        relationInfo.setRelationName("关系1");

        relationInfoDao.save(relationInfo);
    }

    @Test
    void testRelationInfoDelete(){

        relationInfoDao.deleteById(65);
    }

    @Test
    void testRelationInfoGet(){
        System.out.println(relationInfoDao.findAll());
    }

    @Test
    void testRelationInfoGetByID(){
        System.out.println(relationInfoDao.findById(65));
    }

    @Test
    void testGetByName(){
        System.out.println(classInfoDao.findByClassName("ceshi2"));
    }

    @Test
    void testRelationInfoGetByStartName(){
        System.out.println(relationInfoDao.getByStartType("ceshi2"));
    }

    @Test
    void testNeighbour(){
        List<Entity> neighbor = entityDao.getNeighbor(373);
        System.out.println(neighbor);
        List<Relation> neighbour = relationDao.getNeighbor(373);
        System.out.println(neighbour);
    }

    @Test
    void testRelationUpdate11(){
        Relation relation = new Relation();
        relation.setId(68);
        Map map = new HashMap();
        map.put("关系属性21","枚举221222");
        relation.setProperties(map);
        relationDao.update(relation);
    }

    @Test
    void testEntityUpdate(){
        Entity entity = new Entity();
        entity.setId(372);
        Map map = new HashMap();
        map.put("测试222","枚举221");
        entity.setProperties(map);
        entityDao.update(entity);
    }

    @Test
    void test5(){
        List<User> list = userService.list();
        System.out.println(list);
        List<ClassInfo> all = classInfoDao.findAll();
        for (ClassInfo classInfo : all) {
            System.out.println(classInfo);
        }
        List<Entity> cause = entityDao.getByType("故障原因");
        for (Entity entity : cause) {
            System.out.println(entity);
        }
    }
}