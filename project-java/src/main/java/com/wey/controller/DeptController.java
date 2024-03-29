package com.wey.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wey.pojo.Dept;
import com.wey.service.DeptService;
import com.wey.vo.BarVO;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public List<Dept> list() {
        List<Dept> deptList = deptService.queryList();
        return deptList;
    }

    @GetMapping("/listAll")
    public PageInfo<Dept> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Dept> deptList = deptService.queryList();
        PageInfo<Dept> pageInfo = new PageInfo<>(deptList);
        return pageInfo;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Dept dept) {
        Result result = new Result();
        try {
            deptService.add(dept);
            result.setMsg("部门添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Dept dept) {
        Result result = new Result();
        try {
            deptService.edit(dept);
            result.setMsg("部门信息更新成功!");
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
            deptService.delete(id);
            result.setMsg("部门信息删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @GetMapping("/barVO")
    public BarVO barVO() {
        return deptService.barVOList();
    }
}
