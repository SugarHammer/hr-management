package com.wey.service.Impl;

import com.wey.mapper.PositionMapper;
import com.wey.pojo.Position;
import com.wey.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public void add(Position position) {
        positionMapper.add(position);
    }

    @Override
    public void delete(Integer id) {
        positionMapper.delete(id);
    }

    @Override
    public void edit(Position position) {
        positionMapper.edit(position);
    }

    @Override
    public List<Position> queryList() {
        return positionMapper.queryList();
    }
}
