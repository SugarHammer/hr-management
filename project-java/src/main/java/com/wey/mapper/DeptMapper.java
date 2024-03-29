package com.wey.mapper;

import com.wey.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptMapper {

    public void add(Dept dept);

    public void delete(Integer id);

    public void edit(Dept dept);

    public List<Dept> queryList();

    public Dept queryByName(String name);
}
