package com.company.mapper;

import com.company.pojo.entity.PurchaseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PurchaseOrderMapper {


    void insert(PurchaseOrder purchaseOrder);

    @Select("select * from purchase_orders where purchase_order_id = #{purchaseOrderId}")
    PurchaseOrder findById(Integer purchaseOrderId);

    @Update("update purchase_orders set fulfillment = #{fulfillment} where purchase_order_id = #{purchaseOrderId}")
    void purchase(PurchaseOrder purchaseOrder);
}
