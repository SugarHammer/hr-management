package com.wey.service;

import com.wey.pojo.UserRecruit;

import java.util.List;

public interface UserRecruitService {

    public void add(UserRecruit userRecruit);

    public void delete(Integer id);

    public void edit(UserRecruit userRecruit);

    public List<UserRecruit> queryList();

    //根据name查询
    public List<UserRecruit> queryByName(String username);
}
