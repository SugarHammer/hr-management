package com.wey.service;

import com.wey.pojo.User;

import java.util.List;

public interface UserService {

    //查询所有用户信息
    public List<User> queryList();

    //注册
    public void addUser(User user);

    //登录
    public User login(User user);

    //根据用户名查询用户信息
    public User queryByName(String username);

    //更新
    public void edit(User user);
}
