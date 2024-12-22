package com.company.controller;

import com.company.pojo.dto.MenuDTO;
import com.company.pojo.entity.Result;
import com.company.service.menuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MenuController {

    @Autowired
    private menuService menuService;

    @GetMapping("/menu")
    private Result getMenu() {
        log.info("getMenu() called");

        List<MenuDTO> menuList = menuService.list();

        return Result.success(menuList);
    }
}
