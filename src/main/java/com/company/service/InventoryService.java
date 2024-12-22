package com.company.service;

import com.company.pojo.entity.Inventory;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.PurchaseOrder;

import java.util.List;

public interface InventoryService {

    /**
     * 查询所有库存信息
     */
    PageBean list(Integer id, Integer pageNum, Integer pageSize);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    Inventory findById(Integer id);

    /**
     * 创造新的库存
     */
    void create(Inventory inventory);


    /**
     * 根据进货单id进货
     * @param purchaseOrderId
     */
    void purchase(Integer purchaseOrderId);
}
