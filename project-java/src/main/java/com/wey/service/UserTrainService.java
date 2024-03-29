package com.wey.service;

import com.wey.pojo.UserTrain;

import java.util.List;

public interface UserTrainService {

    //查询所有
    List<UserTrain> queryList();

    //根据名字查询所有
    List<UserTrain> queryByName(String username);

    //更新
    void edit(UserTrain userTrain);

    //删除
    void delete(Integer id);

    //添加
    void add(UserTrain userTrain);
}
