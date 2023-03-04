package com.mufiye.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mufiye.domain.Relation;
import com.mufiye.util.JSONUtil;
import com.mufiye.util.SystemUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class GeneralTest {
    @Test
    void test1(){
        System.out.println(TimeUnit.DAYS.toSeconds(1));
    }

    @Test
    void test2() throws InterruptedException {
        System.out.println(SystemUtil.getOccupiedCpu());
        System.out.println(SystemUtil.getSystemFreeMem());
        System.out.println(SystemUtil.getSystemOccupiedMem());
    }

    @Test
    void test3() throws JsonProcessingException {
        Map<String,Integer> map = new HashMap<>();
        map.put("123",123);
        map.put("ceshi1",234);
        String jsonFormat = JSONUtil.getJsonFormat(map);
        System.out.println(jsonFormat);
    }

    @Test
    void test4(){
        Relation relation = new Relation();
        relation.setProperties(new HashMap<>());
        System.out.println(JSONUtil.getJsonFormat(relation));
    }
}
