package com.company.service.impl;

import com.company.constant.PurchaseOrderConstant;
import com.company.mapper.InventoryMapper;
import com.company.mapper.PurchaseOrderMapper;
import com.company.pojo.entity.Inventory;
import com.company.pojo.entity.PurchaseOrder;
import com.company.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    /**
     * 创建新的进货单
     * @param productId
     */
    @Override
    public void create(Integer productId) {

        Inventory inventory = inventoryMapper.findById(productId);

        Integer quantity = PurchaseOrderConstant.PURCHASE_NUM;
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(productId);
        purchaseOrder.setProductName(inventory.getProductName());
        purchaseOrder.setQuantity(quantity);
        purchaseOrder.setPurchasePrice(quantity * inventory.getPurchasePrice());
        purchaseOrder.setFulfillment(PurchaseOrderConstant.NOTPURCHASE);
        purchaseOrder.setDescripiton(PurchaseOrderConstant.NOTHING);
        purchaseOrder.setCreateTime(LocalDateTime.now());
        purchaseOrder.setUpdateTime(LocalDateTime.now());

        purchaseOrderMapper.insert(purchaseOrder);
    }

}
