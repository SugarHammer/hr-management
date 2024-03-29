package com.wey.service.Impl;

import com.wey.mapper.TrainMapper;
import com.wey.mapper.UserTrainMapper;
import com.wey.pojo.Train;
import com.wey.pojo.UserTrain;
import com.wey.service.UserTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserTrainServiceImpl implements UserTrainService {

    @Autowired
    private UserTrainMapper userTrainMapper;
    @Autowired
    private TrainMapper trainMapper;

    @Override
    public List<UserTrain> queryList() {
        return userTrainMapper.queryList();
    }

    @Override
    public List<UserTrain> queryByName(String username) {
        return userTrainMapper.queryByName(username);
    }

    @Override
    public void edit(UserTrain userTrain) {
        userTrainMapper.edit(userTrain);
    }

    @Override
    public void delete(Integer id) {
        UserTrain userTrain = userTrainMapper.queryById(id);
        try {
            userTrainMapper.delete(id);
            Train train = trainMapper.queryById(userTrain.getTid());
            train.setCount(train.getCount() - 1);
            trainMapper.edit(train);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("取消报名失败!");
        }
    }

    @Override
    public void add(UserTrain userTrain) {
        //根据活动id获得集合
        List<UserTrain> userTrainList = userTrainMapper.listByTid(userTrain.getTid());
        int n = 0;
        for (UserTrain train : userTrainList) {
            if (train.getUsername().equals(userTrain.getUsername())) {
                n++;
            }
        }
        if (n == 0) {
            userTrainMapper.add(userTrain);
            Train train = trainMapper.queryById(userTrain.getTid());
            train.setCount(train.getCount() + 1);
            trainMapper.edit(train);
        } else {
            throw new RuntimeException("请勿重复报名!");
        }
    }
}
