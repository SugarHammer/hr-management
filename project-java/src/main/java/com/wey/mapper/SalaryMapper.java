package com.wey.mapper;

import com.wey.pojo.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalaryMapper {

    public void add(Salary salary);

    public void delete(Integer id);

    public void edit(Salary salary);

    public List<Salary> queryList();

    public List<Salary> queryByName(String name);
}
