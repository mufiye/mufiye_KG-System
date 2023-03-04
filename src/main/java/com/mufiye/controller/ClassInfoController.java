package com.mufiye.controller;

import com.mufiye.domain.ClassInfo;
import com.mufiye.service.ClassInfoService;
import com.mufiye.service.EntityService;
import com.mufiye.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("classInfo")
public class ClassInfoController {

    @Autowired
    ClassInfoService classInfoService;
    @Autowired
    EntityService entityService;

    @PostMapping()
    public R add(@RequestBody ClassInfo classInfo){
        classInfoService.add(classInfo);
        return new R(true,null,"添加成功");
    }

    @DeleteMapping()
    public R delete(@RequestBody ClassInfo classInfo){
        classInfoService.deleteById(classInfo.getId());
        return new R(true,null,"删除成功");
    }

    @PutMapping()
    public R update(@RequestBody ClassInfo classInfo){
        classInfoService.updateById(classInfo);
        return new R(true,null,"修改成功");
    }

    @GetMapping("{id}")
    public R get(@PathVariable int id){
        return new R(true,classInfoService.getById(id),"");
    }

    @GetMapping()
    public R getAll(){
        return new R(true,classInfoService.getAll(),"");
    }

    @GetMapping("/getBySession")
    public R getBySession(HttpSession httpSession){
        int id = (int) httpSession.getAttribute("entityId");
        return new R(true,classInfoService.getById(id),"");
    }

    @GetMapping("/className/{name}")
    public R getByName(@PathVariable String name){
        return new R(true,classInfoService.getByName(name),"");
    }

}
