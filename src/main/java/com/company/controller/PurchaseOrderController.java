package com.company.controller;

import com.company.pojo.entity.Result;
import com.company.service.PurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/purchaseOrder")
@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    /**
     * 创建采购单
     * @return
     */
    @PostMapping("/create")
    public Result create(Integer productId){

        log.info("为id为{}的商品创建进货单",productId);

        purchaseOrderService.create(productId);

        return Result.success();
    }
}
