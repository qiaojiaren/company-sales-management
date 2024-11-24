package com.company.service;

public interface PurchaseOrderService {

    /**
     * 创建新的进货单
     * @param productId
     */
    void create(Integer productId);
}
