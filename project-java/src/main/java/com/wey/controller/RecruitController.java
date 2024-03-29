package com.wey.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wey.pojo.Recruit;
import com.wey.service.RecruitService;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @GetMapping("/list")
    public List<Recruit> list() {
        List<Recruit> recruitList = recruitService.queryList();
        return recruitList;
    }

    @GetMapping("/listAll")
    public PageInfo<Recruit> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Recruit> recruitList = recruitService.queryList();
        PageInfo<Recruit> pageInfo = new PageInfo<>(recruitList);
        return pageInfo;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Recruit recruit) {
        Result result = new Result();
        try {
            recruitService.addRecruit(recruit);
            result.setMsg("招聘职位添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Recruit recruit) {
        Result result = new Result();
        try {
            recruitService.edit(recruit);
            result.setMsg("招聘信息更新成功!");
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
            recruitService.delete(id);
            result.setMsg("招聘信息删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }
}
