package com.wey.service.Impl;

import com.wey.mapper.UserRecruitMapper;
import com.wey.pojo.UserRecruit;
import com.wey.service.UserRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRecruitServiceImpl implements UserRecruitService {

    @Autowired
    private UserRecruitMapper userRecruitMapper;

    @Override
    public void add(UserRecruit userRecruit) {
        //根据职位id获取集合
        List<UserRecruit> userRecruits = userRecruitMapper.queryById(userRecruit.getRid());
        int n = 0;
        for (UserRecruit recruit : userRecruits) {
            if (recruit.getUsername().equals(userRecruit.getUsername())) {
                n++;
            }
        }
        if (n == 0) {
            userRecruit.setState("正在进行中");
            userRecruitMapper.add(userRecruit);
        } else {
            throw new RuntimeException("请勿重复申请!");
        }
    }

    @Override
    public void delete(Integer id) {
        userRecruitMapper.delete(id);
    }

    @Override
    public void edit(UserRecruit userRecruit) {
        userRecruitMapper.edit(userRecruit);
    }

    @Override
    public List<UserRecruit> queryList() {
        return userRecruitMapper.queryList();
    }

    @Override
    public List<UserRecruit> queryByName(String username) {
        return userRecruitMapper.queryByName(username);
    }
}
