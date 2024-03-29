package com.wey.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result {

    private Boolean status = true;
    private String msg;
    private Integer userId;
    private String username;
    private String role;
    private String token;
}
