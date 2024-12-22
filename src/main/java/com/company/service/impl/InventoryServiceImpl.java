package com.company.service.impl;

import com.company.constant.PurchaseOrderConstant;
import com.company.mapper.InventoryMapper;
import com.company.mapper.PurchaseOrderMapper;
import com.company.pojo.entity.Contract;
import com.company.pojo.entity.Inventory;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.PurchaseOrder;
import com.company.service.InventoryService;
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
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    /**
     * 查询所有库存
     * @return
     */
    @Override
    public PageBean list(Integer id, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        if(id == null) {
            List<Inventory> inventories = inventoryMapper.list();
            Page<Inventory> p = (Page<Inventory>) inventories;
            long total = p.getTotal();
            List<Inventory> list = p.getResult();
            PageBean pageBean = new PageBean();
            pageBean.setTotal(total);
            pageBean.setRows(list);
            return pageBean;
        }
        else{
            Inventory inventory = inventoryMapper.findById(id);
            PageBean pageBean = new PageBean();
            pageBean.setTotal(1l); // 这里是1，因为只有一个合同
            List<Inventory> inventories = new ArrayList<>();
            inventories.add(inventory);
            pageBean.setRows(inventories);
            return pageBean;
        }
    }

    /**
     * 根据id查产品信息
     * @param id
     * @return
     */
    @Override
    public Inventory findById(Integer id) {
        return inventoryMapper.findById(id);
    }

    /**
     * 创建新的库存
     */
    @Override
    public void create(Inventory inventory) {

        inventory.setCreateTime(LocalDateTime.now());
        inventory.setUpdateTime(LocalDateTime.now());

        inventoryMapper.insert(inventory);
    }

    /**
     * 根据进货单id进货
     * @param purchaseOrderId
     */
    @Override
    public void purchase(Integer purchaseOrderId) {

        //根据id找到进货单
        PurchaseOrder purchaseOrder =purchaseOrderMapper.findById(purchaseOrderId);

        //根据进货单对应商品id找到商品
        Inventory inventory = inventoryMapper.findById(purchaseOrder.getProductId());
        Integer quantity = inventory.getQuantity()+purchaseOrder.getQuantity();
        inventory.setQuantity(quantity);
        inventory.setUpdateTime(LocalDateTime.now());

        inventoryMapper.updateQuantity(inventory);

        //将进货单标位履行
        purchaseOrder.setFulfillment(PurchaseOrderConstant.PURCHASED);
        purchaseOrderMapper.purchase(purchaseOrder);
    }


}

