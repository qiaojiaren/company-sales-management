package com.company.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 发货单实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrder {

    //发货单id
    private Integer deliverOrderId;

    //订单归属的合同的id
    private Integer contractId;

    //商品id
    private Integer productId;

    //商品信息
    private String productInfo;

    //物流信息
    private String logisticsInfo;

    //发货状态
    private String deliveryStatus;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

}
