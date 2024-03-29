package com.wey.pojo;

import lombok.Data;

@Data
public class UserRecruit {

    private Integer id;
    private String username;
    private String sex;
    private String phone;
    private String address;
    private String education;
    private Integer rid;
    private String state;
    private String university;
    private String major;
    private String filename;
    private String fileurl;
    private String newname;

    //职位名称
    private String name;
    //招聘性质
    private String nature;
    private String type;
    //工作地点
    private String gaddress;
    private String describe;
    private String require;
}
