package com.company.controller;

import com.company.pojo.dto.DeliveryOrderDTO;
import com.company.pojo.entity.DeliveryOrder;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.PurchaseOrder;
import com.company.pojo.entity.Result;
import com.company.service.DeliveryOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/delivery")
@RestController
public class DeliveryOrdersController {

    @Autowired
    private DeliveryOrdersService deliveryOrdersService;

    /**
     * 订单发货
     * @param deliveryOrderDTO
     * @return
     */
    @PutMapping("/deliver")
    public Result deliver(@RequestBody DeliveryOrderDTO deliveryOrderDTO){

        log.info("发货单信息：{}",deliveryOrderDTO);

        int isEnough = deliveryOrdersService.deliver(deliveryOrderDTO);
        if (isEnough == 0){
            return Result.error("发货成功，库存预警，生成进货单");
        }else if (isEnough == -1){
            return Result.error("库存不足，发货失败，生成进货单");
        }
        return Result.success();
    }


    /**
     * 查找未发货的订单
     * @return
     */
    @GetMapping("/findNotDeliver")
    public Result findNotDelivery(@RequestParam(value = "query",required = false) Integer id,
                                  @RequestParam(value = "pagenum",required = false) Integer page,
                                  @RequestParam(value = "pagesize",required = false) Integer size){

        log.info("查找未发货的订单");

        PageBean deliveryOrder = deliveryOrdersService.findNotDelivery(id,page,size);
        return Result.success(deliveryOrder);
    }

    /**
     * 分页查询合同数据
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search")
    public Result list(@RequestParam(value = "query",required = false) Integer id,
                       @RequestParam(value = "pagenum",required = false) Integer page,
                       @RequestParam(value = "pagesize",required = false) Integer size){
        log.info("查询合同数据");
        //调用service查询合同数据
        PageBean deliveryOrder = deliveryOrdersService.list(id,page,size);
        return Result.success(deliveryOrder);
    }

    /**
     * 修改发货单
     * @param deliveryOrder
     * @return
     */
    @PutMapping("/modify")
    public Result modify(@RequestBody DeliveryOrder deliveryOrder){
        log.info("修改发货单{}", deliveryOrder);
        deliveryOrdersService.modify(deliveryOrder);
        return Result.success();
    }
}
