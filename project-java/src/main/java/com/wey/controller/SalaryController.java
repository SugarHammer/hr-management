package com.wey.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wey.pojo.Salary;
import com.wey.service.SalaryService;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/list")
    public PageInfo<Salary> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Salary> salaryList = salaryService.queryList();
        PageInfo<Salary> pageInfo = new PageInfo<>(salaryList);
        return pageInfo;
    }

    @GetMapping("/queryByName")
    public List<Salary> queryByName(String name) {
        List<Salary> salaries = salaryService.queryByName(name);
        return salaries;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Salary salary) {
        Result result = new Result();
        try {
            salaryService.add(salary);
            result.setMsg("薪资添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Salary salary) {
        Result result = new Result();
        try {
            salaryService.edit(salary);
            result.setMsg("薪资信息更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @GetMapping("/delete")
    public Result delete(Integer id) {
        Result result = new Result();
        try {
            salaryService.delete(id);
            result.setMsg("薪资信息删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }
}
