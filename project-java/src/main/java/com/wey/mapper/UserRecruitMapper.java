package com.wey.mapper;

import com.wey.pojo.UserRecruit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRecruitMapper {

    public void add(UserRecruit userRecruit);

    public void delete(Integer id);

    public void edit(UserRecruit userRecruit);

    public List<UserRecruit> queryList();

    //根据id查询
    public List<UserRecruit> queryById(Integer rid);

    //根据name查询
    public List<UserRecruit> queryByName(String username);
}
