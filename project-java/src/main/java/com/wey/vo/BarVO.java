package com.wey.vo;

import lombok.Data;

import java.util.List;

@Data
public class BarVO {

    private List<String> names;
    private List<DataVO> values;
}
