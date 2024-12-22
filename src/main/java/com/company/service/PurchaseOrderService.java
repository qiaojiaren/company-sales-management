package com.company.service;

import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.PurchaseOrder;

public interface PurchaseOrderService {

    /**
     * 创建新的进货单
     * @param productId
     */
    void create(Integer productId);

    /**
     * 根据进货单ID查询进货单详情
     * @param id
     * @param page
     * @param size
     * @return
     */
    PageBean list(Integer id, Integer page, Integer size);

    /**
     * 根据进货单ID修改进货单信息
     * @param purchaseOrder
     */
    void modify(PurchaseOrder purchaseOrder);
}
