package com.company.service.impl;

import com.company.constant.ContractStatus;
import com.company.constant.DeliveryStatus;
import com.company.constant.MessageConstant;
import com.company.exception.InventoryNotEnoughException;
import com.company.mapper.ContractsMapper;
import com.company.mapper.DeliveryOrderMapper;
import com.company.mapper.InventoryMapper;
import com.company.mapper.PurchaseOrderMapper;
import com.company.pojo.dto.DeliveryOrderDTO;
import com.company.pojo.entity.*;
import com.company.service.DeliveryOrdersService;
import com.company.service.PurchaseOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    /**
     * 订单发货
     * @param deliveryOrderDTO
     */
    @Override
    public int deliver(DeliveryOrderDTO deliveryOrderDTO) {

        DeliveryOrder deliveryOrder = deliveryOrderMapper.findById(deliveryOrderDTO.getDeliveryOrderId());
        deliveryOrder.setLogisticsInfo(deliveryOrderDTO.getLogisticsInfo());
        deliveryOrder.setComment(deliveryOrderDTO.getComment());

        Inventory inventory = inventoryMapper.findById(deliveryOrder.getProductId());

        int isEnough = 1;

        //判断商品充足和不足
        Integer quantity = inventory.getQuantity() - deliveryOrder.getProductQuantity();
        if(quantity >= 0){
            if(quantity <= inventory.getThreshold()){
                //库存小于阈值，生成采购订单
                purchaseOrderService.create(inventory.getProductId());
                isEnough = 0;
            }
            inventory.setQuantity(quantity);
            inventoryMapper.updateQuantity(inventory);

            deliveryOrder.setDeliveryStatus(DeliveryStatus.DELIVERED);
            deliveryOrder.setUpdateTime(LocalDateTime.now());

            deliveryOrderMapper.deliverById(deliveryOrder);
        }else{
            //库存小于阈值，生成采购订单
            purchaseOrderService.create(inventory.getProductId());
            //商品不足抛出异常
            isEnough = -1;
            //throw new InventoryNotEnoughException(MessageConstant.INVENTORY_NOT_ENOUGH);
        }

        //将未履行的订单变成履行中
        Contract contract =contractsMapper.findById(deliveryOrder.getContractId());
        if (contract.getFulfillmentStatus().equals(ContractStatus.CONTRACT_NON_FULFILLMENT)){
            contract.setFulfillmentStatus(ContractStatus.CONTRACT_PARTIAL_FULFILLMENT);
            contract.setUpdateTime(LocalDateTime.now());

            contractsMapper.fulfillmentById(contract);
        }

        if(deliveryOrderMapper.findFinished(deliveryOrder.getContractId(),DeliveryStatus.NON_DELIVERED) == 0){
            Contract contractFinish = new Contract();
            contractFinish.setContractId(deliveryOrder.getContractId());
            contractFinish.setFulfillmentStatus(ContractStatus.CONTRACT_FULFILLMENT);
            contractsMapper.fulfillmentById(contractFinish);
        }

        return isEnough;
    }

    /**
     * 找到未发货订单
     * @return
     */
    @Override
    public PageBean findNotDelivery(Integer id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        if(id == null) {
            List<DeliveryOrder> deliveryOrders = deliveryOrderMapper.findNotDeliver(DeliveryStatus.NON_DELIVERED);
            Page<DeliveryOrder> p = (Page<DeliveryOrder>) deliveryOrders;
            long total = p.getTotal();
            List<DeliveryOrder> list = p.getResult();
            PageBean pageBean = new PageBean();
            pageBean.setTotal(total);
            pageBean.setRows(list);
            return pageBean;
        }
        else{
            DeliveryOrder deliveryOrder  = deliveryOrderMapper.listNotDeliver(DeliveryStatus.NON_DELIVERED,id);
            PageBean pageBean = new PageBean();
            pageBean.setTotal(1l); // 这里是1，因为只有一个
            List<DeliveryOrder> deliveryOrders = new ArrayList<>();
            deliveryOrders.add(deliveryOrder);
            pageBean.setRows(deliveryOrders);
            return pageBean;
        }
    }


    /**
     * 根据id查询发货单
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageBean list(Integer id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        if(id == null) {
            List<DeliveryOrder> deliveryOrders = deliveryOrderMapper.list();
            Page<DeliveryOrder> p = (Page<DeliveryOrder>) deliveryOrders;
            long total = p.getTotal();
            List<DeliveryOrder> list = p.getResult();
            PageBean pageBean = new PageBean();
            pageBean.setTotal(total);
            pageBean.setRows(list);
            return pageBean;
        }
        else{
            DeliveryOrder deliveryOrder  = deliveryOrderMapper.findById(id);
            PageBean pageBean = new PageBean();
            pageBean.setTotal(1l); // 这里是1，因为只有一个
            List<DeliveryOrder> deliveryOrders = new ArrayList<>();
            deliveryOrders.add(deliveryOrder);
            pageBean.setRows(deliveryOrders);
            return pageBean;
        }
    }

    @Override
    public void modify(DeliveryOrder deliveryOrder) {
        deliveryOrderMapper.update(deliveryOrder);
    }
}
