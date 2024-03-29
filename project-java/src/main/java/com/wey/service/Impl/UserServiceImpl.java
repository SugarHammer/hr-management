package com.wey.service.Impl;

import com.wey.mapper.UserMapper;
import com.wey.pojo.User;
import com.wey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //注册
    @Override
    public void addUser(User user) {
        if (userMapper.queryByName(user.getUsername()) == null) {
            if (user.getRole() == null) {
                user.setRole("普通用户");
            }
            Date date = new Date();
            user.setCreate(date);
            userMapper.addUser(user);
        } else {
            //抛出异常
            throw new RuntimeException("用户名已存在!");
        }
    }

    //登录
    @Override
    public User login(User user) {
        User userDB = userMapper.queryByName(user.getUsername());
        if (userDB != null) {
            if (userDB.getPassword().equals(user.getPassword())) {
                return userDB;
            }
            throw new RuntimeException("密码错误!");
        } else {
            throw new RuntimeException("用户名不存在");
        }
    }

    @Override
    public List<User> queryList() {
        return userMapper.queryList();
    }

    @Override
    public User queryByName(String username) {
        return userMapper.queryByName(username);
    }

    @Override
    public void edit(User user) {
        userMapper.edit(user);
    }
}
