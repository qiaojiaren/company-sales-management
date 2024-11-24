package com.company.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    //进货单id
    private Integer purchaseOrderId;

    //进货商品id
    private Integer productId;

    private String productName;

    //进货数量
    private Integer quantity;

    //采购总价
    private Double purchasePrice;

    //履行状态
    private String fulfillment;

    //描述
    private String descripiton;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
