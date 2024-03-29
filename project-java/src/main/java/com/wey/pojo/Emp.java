package com.wey.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(value = 35) // 表头行高
@ContentRowHeight(value = 30) // 内容行高
public class Emp {

    @ExcelIgnore
    private Integer id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("身份证号")
    private String card;
    @ExcelProperty("手机号")
    private String phone;
    @ExcelProperty("毕业院校")
    private String university;
    @ExcelProperty("专业")
    private String major;
    @ExcelProperty("学历")
    private String education;
    @ExcelProperty("家庭住址")
    private String address;
    @ExcelProperty("职位")
    private String position;
    @ExcelProperty("部门")
    private String dept;
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create;
}
