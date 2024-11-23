package com.company.mapper;

import com.company.pojo.entity.DeliveryOrder;
import org.apache.ibatis.annotations.Mapper;

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

}
