package com.company.mapper;

import com.company.pojo.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 查询购物车所有信息
     * @return
     */
    @Select("select * from shopping_cart")
    List<ShoppingCart> list();

    /**
     * 查询购物车指定信息
     * @return
     */
    @Select("select * from shopping_cart where shopping_cart_id = #{id}")
    ShoppingCart findById(Integer id);


    /**
     * 添加购物车内容
     * @param shoppingCart
     */
    void insert(ShoppingCart shoppingCart);

    /**
     * 清空购物车
     */
    @Delete("delete from shopping_cart")
    void clean();


    /**
     * 根据id删除购物车数据
     * @param id
     */
    @Delete("delete from shopping_cart where shopping_cart_id = #{id}")
    void deleteById(Integer id);

    @Update("update shopping_cart set product_quantity = #{productQuantity} , sell_price = #{sellPrice}," +
            "update_time = #{updateTime} " +
            "where shopping_cart_id = #{shoppingCartId}")
    void updateNumberById(ShoppingCart shoppingCart);
}
