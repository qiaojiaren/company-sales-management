package com.company.service.impl;

import com.company.constant.ContractStatus;
import com.company.constant.DeliveryStatus;
import com.company.mapper.ContractsMapper;
import com.company.mapper.DeliveryOrderMapper;
import com.company.mapper.ShoppingCartMapper;
import com.company.pojo.dto.ContractDTO;
import com.company.pojo.entity.Contract;
import com.company.pojo.entity.DeliveryOrder;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.ShoppingCart;
import com.company.service.ContractsService;
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
public class ContractsServiceImpl implements ContractsService {

    @Autowired
    private ContractsMapper contractsMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;

    /**
     * 查询所有合同
     * @return
     */
    @Override
    public PageBean list(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if(id == null) {
            List<Contract> contracts = contractsMapper.list();
            Page<Contract> p = (Page<Contract>) contracts;
            long total = p.getTotal();
            List<Contract> list = p.getResult();
            PageBean pageBean = new PageBean();
            pageBean.setTotal(total);
            pageBean.setRows(list);
            return pageBean;
        }
        else{
            Contract contract = contractsMapper.findById(id);
            PageBean pageBean = new PageBean();
            pageBean.setTotal(1l); // 这里是1，因为只有一个合同
            List<Contract> contracts = new ArrayList<>();
            contracts.add(contract);
            pageBean.setRows(contracts);
            return pageBean;
        }
    }

    /**
     * 创建新的合同
     * @param contractDTO
     */
    @Override
    public void add(ContractDTO contractDTO) {

        //将合同dto赋值给合同实体
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDTO,contract);

        //将购物车里面的所有物品取出
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.list();


        Double amount = 0.0;
        String content = "商品内容：";

        //计算购物车总金额，生成合同包含的商品内容
        for(ShoppingCart shoppingCart : shoppingCarts){
            amount += shoppingCart.getSellPrice();
            content += shoppingCart.getProductName() + "*" + shoppingCart.getProductQuantity() + ", ";
        }
        //去掉最后的一个逗号
        StringBuilder sb = new StringBuilder(content);
        sb.setCharAt(sb.length()-2,' ');
        content = sb.toString();

        //填写合同实体类，准备写入到数据库
        contract.setContractAmount(amount);
        contract.setContractContent(content);
        contract.setFulfillmentStatus(ContractStatus.CONTRACT_NON_FULFILLMENT);
        contract.setCreateTime(LocalDateTime.now());
        contract.setUpdateTime(LocalDateTime.now());

        //调用mapper插入合同数据
        contractsMapper.insert(contract);


        //插入合同数据后，数据库会自动生成合同id，这时候将id取出，用于制作发货单
        Contract contractLatest = new Contract();
        contractLatest = contractsMapper.findLatest();
        Integer contractId = contractLatest.getContractId();

        //制作发货单
        for(ShoppingCart shoppingCart : shoppingCarts){
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setContractId(contractId);
            deliveryOrder.setProductId(shoppingCart.getProductId());
            deliveryOrder.setProductInfo(shoppingCart.getProductName());
            deliveryOrder.setProductQuantity(shoppingCart.getProductQuantity());
            deliveryOrder.setLogisticsInfo(DeliveryStatus.NO_LOGISTICS);
            deliveryOrder.setDeliveryStatus(DeliveryStatus.NON_DELIVERED);
            deliveryOrder.setCreateTime(LocalDateTime.now());
            deliveryOrder.setUpdateTime(LocalDateTime.now());

            deliveryOrderMapper.insert(deliveryOrder);
        }

        //生成完所有进货单后，强制清空购物车
        shoppingCartMapper.clean();
    }

    /**
     * 根据id删除
     * @param contractId
     */
    @Override
    public void delete(Integer contractId) {
        contractsMapper.deleteById(contractId);
    }

    /**
     * 根据id履行合同
     * @param contractId
     */
    @Override
    public void fulfillment(Integer contractId) {
        Contract contract = contractsMapper.findById(contractId);

        contract.setFulfillmentStatus(ContractStatus.CONTRACT_FULFILLMENT);
        contractsMapper.fulfillmentById(contract);

    }




    /**
     * 修改合同
     * @param contract
     */
    @Override
    public boolean modify(Contract contract) {

        //从数据库中查出原合同，判断是否可以修改
        Contract contract1 = contractsMapper.findById(contract.getContractId());

        if(contract1.getFulfillmentStatus().equals( ContractStatus.CONTRACT_NON_FULFILLMENT)){
            contract.setUpdateTime(LocalDateTime.now());
            contractsMapper.modifyById(contract);
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * 根据销售人员id查未履行合同
     * @param salsepersonId
     * @return
     */
    @Override
    public PageBean unFulfillment(Integer salsepersonId, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<Contract> contracts = contractsMapper.unFulfillment(salsepersonId,ContractStatus.CONTRACT_NON_FULFILLMENT);
        Page<Contract> p = (Page<Contract>) contracts;
        long total = p.getTotal();
        List<Contract> list = p.getResult();
        PageBean pageBean = new PageBean();
        pageBean.setTotal(total);
        pageBean.setRows(list);
        return pageBean;

    }


}
