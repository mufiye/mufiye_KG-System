package com.mufiye.controller;

import com.mufiye.service.EntityService;
import com.mufiye.service.RelationService;
import com.mufiye.util.R;
import com.mufiye.util.SystemUtil;
import com.mufiye.util.TimeUtil;
import com.mufiye.domain.ServerInfo;
import com.mufiye.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    EntityService entityService;
    @Autowired
    RelationService relationService;

    @RequestMapping
    public R getInfo(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setTime(TimeUtil.getCurrentFormatTime());
        serverInfo.setEntityNum(entityService.count());
        serverInfo.setRelationshipNum(relationService.count());
        serverInfo.setFreeMem((int) SystemUtil.getSystemFreeMem());
        serverInfo.setOccupiedMem((int) SystemUtil.getSystemOccupiedMem());
        serverInfo.setOccupiedCpu(SystemUtil.getOccupiedCpu());
        serverInfo.setLastLogin(user.getLastLogin());
        return new R(true,serverInfo,"");
    }

    @RequestMapping("setNeedNodeId/{id}")
    public R setNeedNodeId(@PathVariable int id, HttpSession httpSession){
        httpSession.setAttribute("entityId",id);
        return new R(true,null,"");
    }

    @RequestMapping("setVisualNodeId/{id}")
    public R setVisualNodeId(@PathVariable int id, HttpSession httpSession){
        httpSession.setAttribute("nodeId",id);
        return new R(true,null,"");
    }
}
