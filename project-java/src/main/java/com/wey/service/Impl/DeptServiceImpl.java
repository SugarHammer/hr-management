package com.wey.service.Impl;

import com.wey.mapper.DeptMapper;
import com.wey.pojo.Dept;
import com.wey.service.DeptService;
import com.wey.vo.BarVO;
import com.wey.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void add(Dept dept) {
        dept.setCount(0);
        deptMapper.add(dept);
    }

    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

    @Override
    public void edit(Dept dept) {
        deptMapper.edit(dept);
    }

    @Override
    public List<Dept> queryList() {
        return deptMapper.queryList();
    }

    @Override
    public BarVO barVOList() {
        BarVO barVO = new BarVO();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<DataVO> values = new ArrayList<>();
        List<Dept> depts = deptMapper.queryList();
        for (Dept dept : depts) {
            names.add(dept.getName());
            DataVO dataVO = new DataVO();
            dataVO.setValue(dept.getCount());
            HashMap<String, String> map = new HashMap<>();
            map.put("color", "#3fb1e3");
            dataVO.setItemStyle(map);
            values.add(dataVO);
        }
        barVO.setNames(names);
        barVO.setValues(values);
        return barVO;
    }
}
