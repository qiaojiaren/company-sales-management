package com.company.controller;

import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.PurchaseOrder;
import com.company.pojo.entity.Result;
import com.company.service.PurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询采购单列表  数据
     * @return
     */
    @GetMapping("/search")
    public Result list(@RequestParam(value = "query",required = false) Integer id,
                       @RequestParam(value = "pagenum",required = false) Integer page,
                       @RequestParam(value = "pagesize",required = false) Integer size){
        log.info("查询合同数据");
        //调用service查询合同数据
        PageBean  purchaseOrderList = purchaseOrderService.list(id,page,size);
        return Result.success(purchaseOrderList);
    }

    @PutMapping("/modify")
    public Result modify(@RequestBody PurchaseOrder purchaseOrder){
        log.info("修改采购单{}", purchaseOrder);
        purchaseOrderService.modify(purchaseOrder);
        return Result.success();
    }


}
