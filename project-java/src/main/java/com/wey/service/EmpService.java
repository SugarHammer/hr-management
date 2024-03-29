package com.wey.service;

import com.wey.pojo.Emp;
import com.wey.vo.PieVO;

import java.util.List;

public interface EmpService {

    public void add(Emp emp);

    public void delete(Integer id);

    public void edit(Emp emp);

    public List<Emp> queryList();

    public Emp queryByName(String name);

    public List<Emp> listByName();

    //查询性别查询员工个数
    List<PieVO> queryNum();

    //饼图
    List<PieVO> pieVOList();
}
