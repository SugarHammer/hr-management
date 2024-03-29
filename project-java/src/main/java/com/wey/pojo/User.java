package com.wey.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
//用户表
public class User {

    private Integer id;
    private String username;
    private String password;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create;
    private String role;
}
