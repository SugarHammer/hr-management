package com.wey.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wey.pojo.UserRecruit;
import com.wey.service.UserRecruitService;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/user/recruit")
public class UserRecruitController {

    @Autowired
    private UserRecruitService userRecruitService;

    @PostMapping("/upload")
    public Map<String,Object> fileupload(MultipartFile file, HttpServletRequest req){
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        Map<String, Object> result = new HashMap<>();
        String format = sdf.format(new Date());

        String realPath = req.getServletContext().getRealPath("/") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            file.transferTo(new File(folder, newName));
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + format + newName;
            result.put("status", "OK");
            result.put("name", oldName);
            result.put("url", url);
        } catch (IOException e) {
            result.put("status", "ERROR");
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @GetMapping("/list")
    public PageInfo<UserRecruit> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<UserRecruit> userRecruitList = userRecruitService.queryList();
        PageInfo<UserRecruit> pageInfo = new PageInfo<>(userRecruitList);
        return pageInfo;
    }

    @GetMapping("/queryByName")
    public List<UserRecruit> queryByName(String username) {
        List<UserRecruit> userRecruits = userRecruitService.queryByName(username);
        return userRecruits;
    }

    @PostMapping("/add")
    public Result add(@RequestBody UserRecruit userRecruit) {
        Result result = new Result();
        try {
            userRecruitService.add(userRecruit);
            result.setMsg("申请成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody UserRecruit userRecruit) {
        Result result = new Result();
        try {
            userRecruitService.edit(userRecruit);
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
            userRecruitService.delete(id);
            result.setMsg("取消成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }
}
