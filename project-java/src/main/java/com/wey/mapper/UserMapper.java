package com.wey.mapper;

import com.wey.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //查询所有用户信息
    public List<User> queryList();

    //注册
    public void addUser(User user);

    //根据用户名查询用户信息
    public User queryByName(String username);

    //更新
    public void edit(User user);
}
