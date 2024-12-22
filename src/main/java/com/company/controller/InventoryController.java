package com.company.controller;


import com.company.pojo.entity.Inventory;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.PurchaseOrder;
import com.company.pojo.entity.Result;
import com.company.service.InventoryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/inventory")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * 查询所有库存信息
     * @return
     */
    @GetMapping("/search")
    public Result list(@RequestParam(value = "query",required = false) Integer id,
                       @RequestParam(value = "pagenum",required = false) Integer page,
                       @RequestParam(value = "pagesize",required = false) Integer size){
        log.info("开始查询所有库存信息");
        PageBean inventoryList = inventoryService.list(id,page,size);
        return Result.success(inventoryList);
    }


    /**
     * 创建新的库存
     * @return
     */
    @PostMapping("/create")
    public Result create(@RequestBody Inventory inventory){
        log.info("创建新的库存：{}",inventory);

        inventoryService.create(inventory);
        return Result.success();
    }



    /**
     * 采购进货
     * @return
     */
    @PutMapping("/purchase/{id}")
    public Result purchase(@PathVariable("id") Integer purchaseOrderId){

        log.info("根据进货单进货：{}",purchaseOrderId);

        inventoryService.purchase(purchaseOrderId);
        return Result.success();
    }


}
