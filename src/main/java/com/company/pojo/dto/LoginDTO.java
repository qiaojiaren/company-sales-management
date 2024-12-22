package com.company.pojo.dto;

import lombok.Data;

@Data
public class LoginDTO {

    //token
    private String token;

    //用户名
    private String username;

    //密码
    private String password;

    //是否管理员
    private Integer is_manager;

    //销售人员ID
    private Integer salespersonId;

    //销售人员姓名
    private String salespersonName;

    //销售人员电话
    private String contactNumber;
}
