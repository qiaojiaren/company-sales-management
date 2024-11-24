package com.company.service;

import com.company.pojo.entity.Inventory;

import java.util.List;

public interface InventoryService {

    /**
     * 查询所有库存信息
     */
    List<Inventory> list();

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    Inventory findById(Integer id);
}
