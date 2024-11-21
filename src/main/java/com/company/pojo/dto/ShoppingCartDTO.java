package com.company.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShoppingCartDTO {
    //购物车id
    private Integer shoppingCartId;

    //产品id
    private Integer productId;

    //购买数量
    private Integer productQuantity;

}
