package com.wey.pojo;

import lombok.Data;

@Data
//招聘表
public class Recruit {

    private Integer id;
    private String name;
    private String type;
    private String address;
    private String nature;
    private String describe;
    private String require;
    private Integer number;
}
