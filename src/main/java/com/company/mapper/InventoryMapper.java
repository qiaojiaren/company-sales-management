package com.company.mapper;


import com.company.pojo.entity.Inventory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InventoryMapper {

    /**
     * 查询所有库存
     * @return
     */
    @Select("select * from inventory")
    List<Inventory> list();

    /**
     * 根据id查产品
     * @param id
     * @return
     */
    @Select("select * from inventory where product_id=#{id}")
    Inventory findById(Integer id);

    /**
     * 更新商品数量
     * @param inventory
     */
    @Update("update inventory set quantity = #{quantity} where product_id = #{productId}")
    void updateQuantity(Inventory inventory);

    /**
     * 新增一个库存
     */
    void insert(Inventory inventory);


}
