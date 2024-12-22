package com.company.controller;

import com.company.pojo.dto.LoginDTO;
import com.company.pojo.entity.Result;
import com.company.pojo.entity.Salesperson;
import com.company.service.SalespersonService;
import com.company.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private SalespersonService salespersonService;

    @PostMapping("/login")
    public Result login(@RequestBody Salesperson salesperson){

        log.info("员工登陆：{}",salesperson);
        Salesperson s = salespersonService.login(salesperson);
        LoginDTO loginDTO = new LoginDTO();

        //登陆成功，生成令牌，下发令牌
        if(s!= null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",s.getSalespersonId());
            claims.put("name",s.getSalespersonName());
            claims.put("username",s.getUsername());

            String jwt = JwtUtils.genereateJwt(claims);//jwt包含了当前登陆员工的信息

            loginDTO.setToken(jwt);
            loginDTO.setUsername(s.getUsername());
            loginDTO.setPassword(s.getPassword());
            loginDTO.setSalespersonId(s.getSalespersonId());
            loginDTO.setSalespersonName(s.getSalespersonName());
            loginDTO.setIs_manager(s.getIsManager());
            loginDTO.setContactNumber(s.getContactNumber());

            return Result.success(loginDTO);
        }

        return s != null?Result.success():Result.error("用户名或密码错误");
    }
}
