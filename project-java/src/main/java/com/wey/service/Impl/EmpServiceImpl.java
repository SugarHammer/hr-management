package com.wey.service.Impl;

import com.wey.mapper.DeptMapper;
import com.wey.mapper.EmpMapper;
import com.wey.pojo.Dept;
import com.wey.pojo.Emp;
import com.wey.service.EmpService;
import com.wey.vo.PieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void add(Emp emp) {
        Dept dept = deptMapper.queryByName(emp.getDept());
        try {
            emp.setCreate(new Date());
            empMapper.add(emp);
            dept.setCount(dept.getCount() + 1);
            deptMapper.edit(dept);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("员工信息添加失败!");
        }
    }

    @Override
    public void delete(Integer id) {
        Emp emp = empMapper.queryById(id);
        try {
            empMapper.delete(id);
            Dept dept = deptMapper.queryByName(emp.getDept());
            dept.setCount(dept.getCount() - 1);
            deptMapper.edit(dept);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("员工信息删除失败!");
        }
    }

    @Override
    public void edit(Emp emp) {
        Emp queryEmp = empMapper.queryById(emp.getId());
        Dept dept = deptMapper.queryByName(queryEmp.getDept());
        try {
            if (emp.getDept().equals(queryEmp.getDept())) {
                empMapper.edit(emp);
            } else {
                empMapper.edit(emp);
                //添加更改后的
                Dept dept1 = deptMapper.queryByName(emp.getDept());
                dept1.setCount(dept1.getCount() + 1);
                deptMapper.edit(dept1);

                //去除更改前的
                dept.setCount(dept.getCount() - 1);
                deptMapper.edit(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("员工信息更新失败!");
        }
    }

    @Override
    public List<Emp> queryList() {
        return empMapper.queryList();
    }

    @Override
    public Emp queryByName(String name) {
        return empMapper.queryByName(name);
    }

    @Override
    public List<Emp> listByName() {
        return empMapper.listByName();
    }

    @Override
    public List<PieVO> queryNum() {
        return empMapper.queryNum();
    }

    @Override
    public List<PieVO> pieVOList() {
        ArrayList<PieVO> pieVOList = new ArrayList<>();
        List<PieVO> pies = empMapper.queryNum();
        for (PieVO pie : pies) {
            PieVO pieVO = new PieVO();
            pieVO.setName(pie.getName());
            pieVO.setValue(pie.getValue());
            pieVOList.add(pieVO);
        }
        return pieVOList;
    }
}
