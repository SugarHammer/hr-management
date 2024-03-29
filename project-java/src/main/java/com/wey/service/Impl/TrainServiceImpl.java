package com.wey.service.Impl;

import com.wey.mapper.TrainMapper;
import com.wey.pojo.Train;
import com.wey.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainMapper trainMapper;

    @Override
    public List<Train> queryList() {
        return trainMapper.queryList();
    }

    @Override
    public void edit(Train train) {
        trainMapper.edit(train);
    }

    @Override
    public void delete(Integer id) {
        trainMapper.delete(id);
    }

    @Override
    public void add(Train train) {
        train.setCount(0);
        trainMapper.add(train);
    }
}
