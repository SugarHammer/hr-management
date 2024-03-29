package com.wey.mapper;

import com.wey.pojo.Recruit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecruitMapper {

    public void addRecruit(Recruit recruit);

    public void delete(Integer id);

    public void edit(Recruit recruit);

    public List<Recruit> queryList();
}
