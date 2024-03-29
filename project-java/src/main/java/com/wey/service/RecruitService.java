package com.wey.service;

import com.wey.pojo.Recruit;

import java.util.List;

public interface RecruitService {

    public void addRecruit(Recruit recruit);

    public void delete(Integer id);

    public void edit(Recruit recruit);

    public List<Recruit> queryList();
}
