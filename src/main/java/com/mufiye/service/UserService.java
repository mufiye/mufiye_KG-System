package com.mufiye.service;

import com.mufiye.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 检查用户名
     * @param username 用户名
     * @param password 密码
     * @return 返回用户实体
     */
    User login(String username, String password);

    /**
     * 检查注册是用户名已存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean checkRegister(String username);

    boolean register(User user);

    boolean updateById(User user);

    User getById(int id);

    boolean removeById(int id);

    List<User> list();

    int count();
}
