package com.company.service;


import com.company.pojo.dto.DeliveryOrderDTO;
import com.company.pojo.entity.DeliveryOrder;
import com.company.pojo.entity.PageBean;

import java.util.List;

public interface DeliveryOrdersService {

    /**
     * 订单发货
     */
    int deliver(DeliveryOrderDTO deliveryOrderDTO);

    /**
     * 找到未发货订单
     * @return
     */
    PageBean findNotDelivery(Integer id, Integer page, Integer size);

    /**
     * 分页查询订单
     * @param id
     * @param page
     * @param size
     * @return
     */
    PageBean list(Integer id, Integer page, Integer size);

    /**
     * 修改发货单
     * @param deliveryOrder
     */
    void modify(DeliveryOrder deliveryOrder);
}
