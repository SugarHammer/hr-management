package com.wey.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wey.pojo.User;
import com.wey.service.UserService;
import com.wey.utils.JWTUtil;
import com.wey.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Result result = new Result();
        try {
            User userDB = userService.login(user);

            //生成token
            Map<String, String> payload = new HashMap<>();
            payload.put("username", userDB.getUsername());
            String token = JWTUtil.getToken(payload);

            result.setMsg("登录成功")
                    .setUserId(userDB.getId())
                    .setUsername(userDB.getUsername())
                    .setRole(userDB.getRole())
                    .setToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage()).setToken("");
        }
        return result;
    }

    //验证token
    @GetMapping("/cheack")
    public Result cheack(HttpServletRequest request) {
        Result result = new Result();
        try{
            String token = request.getHeader("token");
            JWTUtil.verify(token);
            result.setMsg("请求成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("token验证失败,请重新登录!");
        }
        return result;
    }

    //注册
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        Result result = new Result();
        try {
            userService.addUser(user);
            result.setMsg("注册成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    //查询所有用户信息
    @GetMapping("/list")
    public PageInfo<User> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<User> userList = userService.queryList();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    //根据名字查询用户信息
    @GetMapping("/queryByName")
    public User queryByName(String username) {
        return userService.queryByName(username);
    }

    //更新
    @PostMapping("/edit")
    public Result edit(@RequestBody User user) {
        Result result = new Result();
        try {
            userService.edit(user);
            result.setMsg("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    //退出
    @GetMapping("/logout")
    public Result logout() {
        return new Result();
    }

}
