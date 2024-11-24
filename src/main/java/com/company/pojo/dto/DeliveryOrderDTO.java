package com.company.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class DeliveryOrderDTO {

    //发货单id
    private Integer deliveryOrderId;

    //订单归属的合同的id
    private Integer contractId;

    //发货的商品id
    private Integer productId;

    //发货的商品名字
    private Integer productInfo;

    //物流信息
    private String logisticsInfo;

    //发货状态
    private String deliveryStatus;

    //备注
    private String comment;




}