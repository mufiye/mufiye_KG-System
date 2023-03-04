package com.mufiye.controller;


import com.mufiye.util.R;
import com.mufiye.domain.User;
import com.mufiye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户退出登录 任何权限可访问
     * @param httpSession
     * @return
     */
    @RequestMapping("logout")
    public R logout(HttpSession httpSession){
        httpSession.invalidate();
        return new R(true,null,"成功退出，即将跳转......");
    }

    /**
     * 用户登录 任何权限可访问
     * @param passUser 封装的用户信息
     * @param remember 记住登录
     * @param httpSession
     * @return
     */
    @RequestMapping("login")
    public R login(@RequestBody User passUser,boolean remember, HttpSession httpSession){
        R r = new R();
        User user = userService.login(passUser.getUsername(), passUser.getPassword());
        if(user!=null){
            httpSession.setAttribute("user",user);
            if(remember){
                httpSession.setMaxInactiveInterval((int) TimeUnit.DAYS.toSeconds(1));
            }
            r.setData(user);
            r.setFlag(true);
            r.setMessage("登录成功，即将进入主页！");
        }else{
            r.setMessage("用户名或密码错误！");
        }
        return r;
    }

    /**
     * 注册 任何权限可访问
     * @param user
     * @return
     */
    @RequestMapping("register")
    public R register(@RequestBody User user){
        user.setRole("user");
        boolean register = userService.register(user);
        return new R(register,null,register?"注册成功，即将前往登录界面...":"注册失败,该用户名已存在");
    }

    /**
     * 获取当前登录的用户信息 已登录user和admin用户可访问
     * @param httpSession
     * @return
     */
    @RequestMapping("/getUser")
    public R getUser(HttpSession httpSession){
        Object user = httpSession.getAttribute("user");
        return new R(user!=null,user,"");
    }

    /**
     * 获取指定id的用户 已登录的admin用户可访问
     * @param id
     * @return
     */
    @RequestMapping("/getUser/{id}")
    public R getUser(@PathVariable int id){
        User user = userService.getById(id);
        return new R(user!=null,user,"");
    }

    /**
     * 更新指定id的用户信息 已登录user和admin用户可访问
     * @param user
     * @param httpSession
     * @return
     */
    @RequestMapping("update")
    public R update(@RequestBody User user,HttpSession httpSession){
        boolean update = userService.updateById(user);
        if(update){
            httpSession.setAttribute("user",user);
        }
        return new R(update,null,update?"更新信息成功！":"更新信息失败，请联系管理员");
    }

    /**
     * 获取所有用户信息 已登录admin用户可访问
     * @return
     */
    @RequestMapping("getAllUser")
    public R getAllUser(){
        List<User> userList = userService.list();
        return new R(userList.size()>0,userList,"");
    }

    /**
     * 获取指定id用户信息 已登录admin用户可访问
     * @param id
     * @return
     */
    @RequestMapping("{id}")
    public R delete(@PathVariable int id){
        boolean b = userService.removeById(id);
        return new R(b,null,b?"删除成功":"删除失败");
    }
}
