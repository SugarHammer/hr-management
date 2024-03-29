package com.wey.service;

import com.wey.pojo.Dept;
import com.wey.vo.BarVO;

import java.util.List;

public interface DeptService {

    public void add(Dept dept);

    public void delete(Integer id);

    public void edit(Dept dept);

    public List<Dept> queryList();

    //树状图
    BarVO barVOList();
}
