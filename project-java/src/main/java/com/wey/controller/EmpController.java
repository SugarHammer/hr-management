package com.wey.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wey.pojo.Emp;
import com.wey.service.EmpService;
import com.wey.vo.PieVO;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/export")
    public Result exportExcel() {
        Result result = new Result();
        try {
            String PATH = "C:\\Users\\17136\\Desktop\\";
            String fileName = PATH + "员工信息表.xlsx";
            List<Emp> emps = empService.queryList();
            EasyExcel.write(fileName,Emp.class)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) //自适应宽度, 不是很准确
                    .sheet()
                    .doWrite(emps);
            result.setMsg("Excel导出成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @GetMapping("/list")
    public PageInfo<Emp> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Emp> empList = empService.queryList();
        PageInfo<Emp> pageInfo = new PageInfo<>(empList);
        return pageInfo;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Emp emp) {
        Result result = new Result();
        try {
            empService.add(emp);
            result.setMsg("员工添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Emp emp) {
        Result result = new Result();
        try {
            empService.edit(emp);
            result.setMsg("员工信息更新成功!");
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
            empService.delete(id);
            result.setMsg("员工信息删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @GetMapping("/queryByName")
    public Emp queryByName(String name) {
        Emp emp = empService.queryByName(name);
        return emp;
    }

    @GetMapping("/listByName")
    public PageInfo<Emp> listByName(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Emp> empList = empService.listByName();
        PageInfo<Emp> pageInfo = new PageInfo<>(empList);
        return pageInfo;
    }

    @GetMapping("/pieVO")
    public List<PieVO> pieVO() {
        return empService.pieVOList();
    }
}
