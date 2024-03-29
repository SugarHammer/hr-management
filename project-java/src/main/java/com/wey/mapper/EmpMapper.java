package com.wey.mapper;

import com.wey.pojo.Emp;
import com.wey.vo.PieVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmpMapper {

    public void add(Emp emp);

    public void delete(Integer id);

    public void edit(Emp emp);

    public List<Emp> queryList();

    public Emp queryById(Integer id);

    public Emp queryByName(String name);

    public List<Emp> listByName();

    //查询性别查询员工个数
    List<PieVO> queryNum();
}
