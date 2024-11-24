package com.company.controller;

import com.company.pojo.dto.DeliveryOrderDTO;
import com.company.pojo.entity.DeliveryOrder;
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

        deliveryOrdersService.deliver(deliveryOrderDTO);
        return Result.success();
    }


    @GetMapping("/findNotDeliver")
    public Result findNotDelivery(){

        log.info("查找未发货的订单");

        List<DeliveryOrder> deliveryOrder = deliveryOrdersService.findNotDelivery();
        return Result.success(deliveryOrder);
    }
}
