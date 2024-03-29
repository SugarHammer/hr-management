package com.wey.service.Impl;

import com.wey.mapper.RecruitMapper;
import com.wey.pojo.Recruit;
import com.wey.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;

    @Override
    public void addRecruit(Recruit recruit) {
        recruitMapper.addRecruit(recruit);
    }

    @Override
    public void delete(Integer id) {
        recruitMapper.delete(id);
    }

    @Override
    public void edit(Recruit recruit) {
        recruitMapper.edit(recruit);
    }

    @Override
    public List<Recruit> queryList() {
        return recruitMapper.queryList();
    }
}
