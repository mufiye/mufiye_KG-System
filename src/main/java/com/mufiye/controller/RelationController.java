package com.mufiye.controller;

import com.mufiye.domain.Relation;
import com.mufiye.service.RelationService;
import com.mufiye.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("relation")
public class RelationController {
    @Autowired
    RelationService relationService;

    @PostMapping()
    public R add(@RequestBody Relation relation){
        relationService.save(relation);
        return new R(true,null,"添加成功");
    }
    @DeleteMapping()
    public R delete(@RequestBody Relation relation){
        relationService.delete((int) relation.getId());
        return new R(true,null,"删除成功");
    }
    @PutMapping()
    public R put(@RequestBody Relation relation){
        relationService.update(relation);
        return new R(true,null,"修改成功");
    }
    @GetMapping("{id}")
    public R getByID(@PathVariable int id){
        return new R(true,relationService.getById(id),"");
    }

    @GetMapping("/sourceId/{sourceId}")
    public R getByType(@PathVariable int sourceId){
        return new R(true,relationService.getBySourceId(sourceId),"");
    }

    @GetMapping("/type/{startType}/{endType}")
    public R getByType(@PathVariable String startType, @PathVariable String endType){
        return new R(true,relationService.getByType(startType, endType),"");
    }
    @GetMapping("/relationType/{typeName}")
    public R getByRelationType(@PathVariable String typeName){
        return new R(true,relationService.getByRelationType(typeName),"");
    }

    @GetMapping("/neighbor/{id}")
    public R getNeighbor(@PathVariable int id){
        return new R(true,relationService.getNeighbor(id),"");
    }


}
