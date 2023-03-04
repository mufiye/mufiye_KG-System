package com.mufiye.service.impl;

import com.mufiye.dao.UserDao;
import com.mufiye.domain.User;
import com.mufiye.service.UserService;
import com.mufiye.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public User login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        List<User> users = userDao.findAll(Example.of(user));
        if(!users.isEmpty()){
            user = users.get(0);
            User clone = (User) user.clone();
            clone.setLastLogin(TimeUtil.getCurrentFormatTime());
            userDao.save(clone);
            return user;
        }
        return null;
    }

    public boolean checkRegister(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> users = userDao.findAll(Example.of(user));
        return !users.isEmpty();
    }

    public boolean register(User user) {
        boolean checkRegister = checkRegister(user.getUsername());
        if(checkRegister){
            //用户名已存在
            return false;
        }
        else{
            try {
                userDao.save(user);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

    }

    @Override
    public boolean updateById(User user) {

        try {
            userDao.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int id) {
        return userDao.findById((long) id).get();
    }

    @Override
    public boolean removeById(int id) {
        try {
            userDao.deleteById((long) id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> list() {
        return userDao.findAll();
    }

    @Override
    public int count() {
        return (int) userDao.count();
    }
}
