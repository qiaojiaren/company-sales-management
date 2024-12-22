package com.company.mapper;

import com.company.pojo.entity.DeliveryOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 发货单
 */
@Mapper
public interface DeliveryOrderMapper {

    /**
     * 创建发货单
     * @param deliveryOrder
     */
    void insert(DeliveryOrder deliveryOrder);


    /**
     * 根据id找发货单
     * @param deliveryOrderId
     * @return
     */
    @Select("select * from delivery_orders where delivery_order_id = #{deliveryOrderId}")
    DeliveryOrder findById(Integer deliveryOrderId);

    /**
     * 找到未发货的订单
     * @return
     */
    @Select("select * from delivery_orders where delivery_status = #{deliverStatus}")
    List<DeliveryOrder> findNotDeliver(String deliverStatus);

    /**
     * 找到未发货的订单
     * @return
     */
    @Select("select * from delivery_orders where delivery_status = #{deliverStatus} and delivery_order_id = #{id}")
    DeliveryOrder listNotDeliver(String deliverStatus, Integer id);

    /**
     * 根据id发货
     */
    @Update("update delivery_orders set logistics_info = #{logisticsInfo}," +
            "comment = #{comment} , delivery_status = #{deliveryStatus}," +
            "update_time = #{updateTime} where delivery_order_id = #{deliveryOrderId}")
    void deliverById(DeliveryOrder deliveryOrder);

    /**
     * 分页查询发货单
     * @return
     */
    @Select("select * from delivery_orders")
    List<DeliveryOrder> list();

    /**
     * 更新发货单
     * @param deliveryOrder
     */
    void update(DeliveryOrder deliveryOrder);

    /**
     * 根据合同id和发货状态查询发货单数量
     * @param contractId
     * @param nonDelivered
     * @return
     */
    @Select("select count(*) from delivery_orders " +
            "where contract_id = #{contractId} and delivery_status = #{nonDelivered}")
    int findFinished(Integer contractId, String nonDelivered);
}
