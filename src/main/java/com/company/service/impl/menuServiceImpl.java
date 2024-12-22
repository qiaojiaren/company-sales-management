package com.company.service.impl;

import com.company.mapper.menuMapper;
import com.company.pojo.dto.MenuDTO;
import com.company.pojo.entity.Menu;
import com.company.pojo.entity.ShoppingCart;
import com.company.service.menuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class menuServiceImpl implements menuService {

    @Autowired
    private com.company.mapper.menuMapper menuMapper;

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<MenuDTO> list() {

        List<Menu> menus = menuMapper.findParents();
        List<MenuDTO> menuDTOS = new ArrayList<>();
//        BeanUtils.copyProperties(menus, menuDTOS);

        for(Menu menu : menus){
             MenuDTO dto = new MenuDTO();
             BeanUtils.copyProperties(menu, dto);
             dto.setChildren(menuMapper.findChildren(dto.getId()));
             menuDTOS.add(dto);
        }
        return menuDTOS;
    }
}
