package com.mufiye.controller;

import com.mufiye.domain.Entity;
import com.mufiye.service.EntityService;
import com.mufiye.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("entity")
public class EntityController {

    @Autowired
    EntityService entityService;

    @PostMapping()
    public R add(@RequestBody Entity entity){
        entityService.save(entity);
        return new R(true,null,"添加成功");
    }
    @DeleteMapping("{id}")
    public R delete(@PathVariable int id){
        entityService.delete(id);
        return new R(true,null,"删除成功");
    }
    @PutMapping()
    public R put(@RequestBody Entity entity){
        entityService.update(entity);
        return new R(true,null,"修改成功");
    }
    @GetMapping("{id}")
    public R getByID(@PathVariable int id){
        return new R(true,entityService.getById(id),"");
    }

    @GetMapping("/type/{typeName}")
    public R getByType(@PathVariable String typeName){

        return new R(true,entityService.getByType(typeName),"");
    }

    @GetMapping("/neighbor/{id}")
    public R getNeighbor(@PathVariable int id){
        return new R(true,entityService.getNeighbor(id),"");
    }

    @GetMapping("/getBySession")
    public R getBySession(HttpSession httpSession){
        int id = (int) httpSession.getAttribute("nodeId");
        return new R(true,entityService.getById(id),"");
    }
}
