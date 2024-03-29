package com.wey.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserTrain {

    private Integer id;
    private String username;
    private Integer tid;

    private String position;
    private String dept;
    //活动名称
    private String name;
    private String introduce;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end;
}
