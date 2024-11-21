package com.company.service.impl;

import com.company.mapper.InventoryMapper;
import com.company.pojo.entity.Inventory;
import com.company.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 查询所有库存
     * @return
     */
    @Override
    public List<Inventory> list() {
        return inventoryMapper.list();
    }

    /**
     * 根据id查产品信息
     * @param id
     * @return
     */
    @Override
    public Inventory getById(Integer id) {
        return inventoryMapper.getById(id);
    }
}
