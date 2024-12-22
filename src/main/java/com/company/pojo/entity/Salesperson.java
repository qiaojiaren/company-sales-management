package com.company.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Salesperson {

    //id
    private Integer salespersonId;

    //name
    private String salespersonName;

    //联系方式
    private String contactNumber;

    private Integer isManager;

    //用户名
    private String username;

    //密码
    private String password;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
