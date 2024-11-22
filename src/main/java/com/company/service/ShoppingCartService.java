package com.company.service;

import com.company.pojo.dto.ShoppingCartDTO;
import com.company.pojo.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 查询购物车所有信息
     * @return
     */
    List<ShoppingCart> list();

    /**
     * 添加购物车内容
     * @param shoppingCartDTO
     */
    void add(ShoppingCartDTO shoppingCartDTO);

    /**
     * 清空购物车
     */
    void cleanShoppingCart();

    /**
     * 删除购物车中第一个商品
     * @param shoppingCartDTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 购物车内容加一
     * @param shoppingCartDTO
     */
    void plusShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
