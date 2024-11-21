package com.company.mapper;


import com.company.pojo.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    Inventory getById(Integer id);


}
