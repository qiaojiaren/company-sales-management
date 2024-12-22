package com.company.mapper;

import com.company.pojo.entity.PurchaseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PurchaseOrderMapper {


    void insert(PurchaseOrder purchaseOrder);

    /**
     * 根据id查询采购订单
     * @param purchaseOrderId
     * @return
     */
    @Select("select * from purchase_orders where purchase_order_id = #{purchaseOrderId}")
    PurchaseOrder findById(Integer purchaseOrderId);

    @Update("update purchase_orders set fulfillment = #{fulfillment} where purchase_order_id = #{purchaseOrderId}")
    void purchase(PurchaseOrder purchaseOrder);

    /**
     * 查询所有采购订单
     * @return
     */
    @Select("select * from purchase_orders")
    List<PurchaseOrder> list();

    /**
     * 更新进货单
     * @param purchaseOrder
     */
    void update(PurchaseOrder purchaseOrder);
}
