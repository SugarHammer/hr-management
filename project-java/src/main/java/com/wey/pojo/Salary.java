package com.wey.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Salary {

    private Integer id;
    private String name;
    private String position;
    private String dept;
    private Double pay;
    private Double bonus;
    private Double sum;
    private Double tax;
    private Double insurance;
    private Double salary;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date release;
}
