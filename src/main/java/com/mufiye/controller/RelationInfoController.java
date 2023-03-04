package com.mufiye.controller;

import com.mufiye.domain.RelationInfo;
import com.mufiye.service.RelationInfoService;
import com.mufiye.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("relationInfo")
public class RelationInfoController {

    @Autowired
    RelationInfoService relationInfoService;

    @PostMapping()
    public R add(@RequestBody RelationInfo relationInfo) {
        relationInfoService.add(relationInfo);
        return new R(true, null, "添加成功");
    }

    @DeleteMapping()
    public R delete(@RequestBody RelationInfo relationInfo) {
        relationInfoService.deleteById(relationInfo.getId());
        return new R(true, null, "删除成功");
    }

    @PutMapping()
    public R update(@RequestBody RelationInfo relationInfo) {
        relationInfoService.updateById(relationInfo);
        return new R(true, null, "修改成功");
    }

    @GetMapping("{id}")
    public R get(@PathVariable int id) {
        return new R(true, relationInfoService.getById(id), "");
    }

    @GetMapping()
    public R getAll() {
        return new R(true, relationInfoService.getAll(), "");
    }

    @GetMapping("/type/{startType}")
    public R getByStartType(@PathVariable String startType) {
        return new R(true, relationInfoService.getByStartType(startType), "");
    }

    @GetMapping("/relationName/{name}")
    public R getByName(@PathVariable String name) {
        return new R(true, relationInfoService.getByName(name), "");
    }
}