package com.wey.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Train {

    private Integer id;
    private String name;
    private String introduce;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end;
    private String state;
    private Integer count;
}
