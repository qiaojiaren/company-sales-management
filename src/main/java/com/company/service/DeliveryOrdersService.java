package com.company.service;


import com.company.pojo.dto.DeliveryOrderDTO;
import com.company.pojo.entity.DeliveryOrder;

import java.util.List;

public interface DeliveryOrdersService {

    /**
     * 订单发货
     */
    void deliver(DeliveryOrderDTO deliveryOrderDTO);

    /**
     * 找到未发货订单
     * @return
     */
    List<DeliveryOrder> findNotDelivery();
}
