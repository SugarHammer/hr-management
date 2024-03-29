package com.wey.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wey.pojo.Position;
import com.wey.service.PositionService;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/list")
    public List<Position> list() {
        List<Position> positionList = positionService.queryList();
        return positionList;
    }

    @GetMapping("/listAll")
    public PageInfo<Position> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Position> positionList = positionService.queryList();
        PageInfo<Position> pageInfo = new PageInfo<>(positionList);
        return pageInfo;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Position position) {
        Result result = new Result();
        try {
            positionService.add(position);
            result.setMsg("职位添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Position position) {
        Result result = new Result();
        try {
            positionService.edit(position);
            result.setMsg("职位信息更新成功!");
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
            positionService.delete(id);
            result.setMsg("职位信息删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }


}
