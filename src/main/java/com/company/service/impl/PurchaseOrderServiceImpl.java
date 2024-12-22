package com.company.service.impl;

import com.company.constant.PurchaseOrderConstant;
import com.company.mapper.InventoryMapper;
import com.company.mapper.PurchaseOrderMapper;
import com.company.pojo.entity.Contract;
import com.company.pojo.entity.Inventory;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.PurchaseOrder;
import com.company.service.PurchaseOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        purchaseOrder.setDescription(PurchaseOrderConstant.NOTHING);
        purchaseOrder.setCreateTime(LocalDateTime.now());
        purchaseOrder.setUpdateTime(LocalDateTime.now());

        purchaseOrderMapper.insert(purchaseOrder);
    }



    /**
     * 根据id查询进货单
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageBean list(Integer id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        if(id == null) {
            List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.list();
            Page<PurchaseOrder> p = (Page<PurchaseOrder>) purchaseOrders;
            long total = p.getTotal();
            List<PurchaseOrder> list = p.getResult();
            PageBean pageBean = new PageBean();
            pageBean.setTotal(total);
            pageBean.setRows(list);
            return pageBean;
        }
        else{
            PurchaseOrder purchaseOrder = purchaseOrderMapper.findById(id);
            PageBean pageBean = new PageBean();
            pageBean.setTotal(1l); // 这里是1，因为只有一个合同
            List<PurchaseOrder> purchaseOrders = new ArrayList<>();
            purchaseOrders.add(purchaseOrder);
            pageBean.setRows(purchaseOrders);
            return pageBean;
        }
    }

    /**
     * 修改进货单
     * @param purchaseOrder
     */
    @Override
    public void modify(PurchaseOrder purchaseOrder) {
        purchaseOrderMapper.update(purchaseOrder);
    }

}
