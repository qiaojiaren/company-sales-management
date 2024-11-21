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
public class ShoppingCart {

    //购物车id
    private Integer shoppingCartId;

    //产品id
    private Integer productId;

    //产品名字
    private String productName;

    //购买数量
    private Integer productQuantity;

    //总价
    private Double sellPrice;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;
}
