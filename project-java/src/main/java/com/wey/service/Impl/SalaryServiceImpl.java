package com.wey.service.Impl;

import com.wey.mapper.SalaryMapper;
import com.wey.pojo.Salary;
import com.wey.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public void add(Salary salary) {
        //时间
        salary.setRelease(new Date());
        //总工资
        salary.setSum(salary.getPay() + salary.getBonus());
        //到手工资
        salary.setSalary(salary.getPay() + salary.getBonus() - salary.getTax() - salary.getInsurance());
        salaryMapper.add(salary);
    }

    @Override
    public void delete(Integer id) {
        salaryMapper.delete(id);
    }

    @Override
    public void edit(Salary salary) {
        //总工资
        salary.setSum(salary.getPay() + salary.getBonus());
        //到手工资
        salary.setSalary(salary.getPay() + salary.getBonus() - salary.getTax() - salary.getInsurance());
        salaryMapper.edit(salary);
    }

    @Override
    public List<Salary> queryList() {
        return salaryMapper.queryList();
    }

    @Override
    public List<Salary> queryByName(String name) {
        return salaryMapper.queryByName(name);
    }
}
