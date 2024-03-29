package com.wey.service;

import com.wey.pojo.Train;

import java.util.List;

public interface TrainService {

    //查询所有
    List<Train> queryList();

    //更新
    void edit(Train train);

    //删除
    void delete(Integer id);

    //添加
    void add(Train train);
}
