package com.company.service;

import com.company.pojo.dto.MenuDTO;

import java.util.List;

public interface menuService {

    /**
     * 返回彩蛋
     * @return
     */
    List<MenuDTO> list();
}
