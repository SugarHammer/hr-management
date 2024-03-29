package com.wey.service;

import com.wey.pojo.Position;

import java.util.List;

public interface PositionService {

    public void add(Position position);

    public void delete(Integer id);

    public void edit(Position position);

    public List<Position> queryList();
}
