package com.wey.mapper;

import com.wey.pojo.Position;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionMapper {

    public void add(Position position);

    public void delete(Integer id);

    public void edit(Position position);

    public List<Position> queryList();
}
