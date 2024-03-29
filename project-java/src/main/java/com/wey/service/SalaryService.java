package com.wey.service;

import com.wey.pojo.Salary;

import java.util.List;

public interface SalaryService {

    public void add(Salary salary);

    public void delete(Integer id);

    public void edit(Salary salary);

    public List<Salary> queryList();

    public List<Salary> queryByName(String name);
}
