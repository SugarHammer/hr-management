package com.wey.mapper;

import com.wey.pojo.Train;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TrainMapper {

    //查询所有
    public List<Train> queryList();

    //根据id查询培训活动
    public Train queryById(Integer id);

    //更新
    public void edit(Train train);

    //删除
    public void delete(Integer id);

    //添加
    public void add(Train train);
}
