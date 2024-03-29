package com.wey.mapper;

import com.wey.pojo.UserTrain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserTrainMapper {

    //查询所有
    public List<UserTrain> queryList();

    //根据名字查询所有
    public List<UserTrain> queryByName(String username);

    //根据tid查询所有
    public List<UserTrain> listByTid(Integer tid);

    //根据id查询
    public UserTrain queryById(Integer id);

    //更新
    public void edit(UserTrain userTrain);

    //删除
    public void delete(Integer id);

    //添加
    public void add(UserTrain userTrain);
}
