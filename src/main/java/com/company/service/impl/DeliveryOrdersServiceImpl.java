package com.company.service.impl;

import com.company.constant.ContractStatus;
import com.company.constant.DeliveryStatus;
import com.company.constant.MessageConstant;
import com.company.exception.InventoryNotEnoughException;
import com.company.mapper.ContractsMapper;
import com.company.mapper.DeliveryOrderMapper;
import com.company.mapper.InventoryMapper;
import com.company.pojo.dto.DeliveryOrderDTO;
import com.company.pojo.entity.Contract;
import com.company.pojo.entity.DeliveryOrder;
import com.company.pojo.entity.Inventory;
import com.company.service.DeliveryOrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DeliveryOrdersServiceImpl implements DeliveryOrdersService {

    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private ContractsMapper contractsMapper;

    /**
     * 订单发货
     * @param deliveryOrderDTO
     */
    @Override
    public void deliver(DeliveryOrderDTO deliveryOrderDTO) {

        DeliveryOrder deliveryOrder = deliveryOrderMapper.findById(deliveryOrderDTO.getDeliveryOrderId());
        deliveryOrder.setLogisticsInfo(deliveryOrderDTO.getLogisticsInfo());
        deliveryOrder.setComment(deliveryOrderDTO.getComment());

        Inventory inventory = inventoryMapper.findById(deliveryOrder.getProductId());

        //判断商品充足和不足
        Integer quantity = inventory.getQuantity() - deliveryOrder.getProductQuantity();
        if(quantity >= 0){
            if(quantity <= 5){
                //警告
            }
            inventory.setQuantity(quantity);
            inventoryMapper.updateQuantity(inventory);

            deliveryOrder.setDeliveryStatus(DeliveryStatus.DELIVERED);
            deliveryOrder.setUpdateTime(LocalDateTime.now());

            deliveryOrderMapper.deliverById(deliveryOrder);
        }else{
            //商品不足抛出异常
            throw new InventoryNotEnoughException(MessageConstant.INVENTORY_NOT_ENOUGH);
        }

        //将未履行的订单变成履行中
        Contract contract =contractsMapper.findById(deliveryOrder.getContractId());
        if (contract.getFulfillmentStatus().equals(ContractStatus.CONTRACT_NON_FULFILLMENT)){
            contract.setFulfillmentStatus(ContractStatus.CONTRACT_PARTIAL_FULFILLMENT);
            contract.setUpdateTime(LocalDateTime.now());

            contractsMapper.fulfillmentById(contract);
        }


    }

    /**
     * 找到未发货订单
     * @return
     */
    @Override
    public List<DeliveryOrder> findNotDelivery() {

        return deliveryOrderMapper.findNotDeliver(DeliveryStatus.NON_DELIVERED);
    }
}
