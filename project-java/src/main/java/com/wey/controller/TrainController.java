package com.wey.controller;

import com.wey.pojo.Train;
import com.wey.service.TrainService;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/list")
    public List<Train> list() {
        List<Train> trainList = trainService.queryList();
        return trainList;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Train train) {
        Result result = new Result();
        try {
            trainService.add(train);
            result.setMsg("活动添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Train train) {
        Result result = new Result();
        try {
            trainService.edit(train);
            result.setMsg("活动信息更新成功!");
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
            trainService.delete(id);
            result.setMsg("活动信息删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }
}
