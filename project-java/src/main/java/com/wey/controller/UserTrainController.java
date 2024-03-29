package com.wey.controller;

import com.wey.pojo.UserTrain;
import com.wey.service.UserTrainService;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user/train")
public class UserTrainController {

    @Autowired
    private UserTrainService userTrainService;

    @GetMapping("/list")
    public List<UserTrain> list() {
        List<UserTrain> userTrainList = userTrainService.queryList();
        return userTrainList;
    }

    @GetMapping("/queryByName")
    public List<UserTrain> queryByName(String username) {
        List<UserTrain> userTrainList = userTrainService.queryByName(username);
        return userTrainList;
    }

    @PostMapping("/add")
    public Result add(@RequestBody UserTrain userTrain) {
        Result result = new Result();
        try {
            userTrainService.add(userTrain);
            result.setMsg("报名成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody UserTrain userTrain) {
        Result result = new Result();
        try {
            userTrainService.edit(userTrain);
            result.setMsg("更新成功!");
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
            userTrainService.delete(id);
            result.setMsg("取消成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }
}
