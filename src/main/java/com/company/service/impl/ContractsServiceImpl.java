package com.company.service.impl;

import com.company.constant.Contract_status;
import com.company.mapper.ContractsMapper;
import com.company.mapper.InventoryMapper;
import com.company.pojo.entity.Contract;
import com.company.service.ContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContractsServiceImpl implements ContractsService {

    @Autowired
    private ContractsMapper contractsMapper;

    @Autowired
    private InventoryMapper inventoryMapper;


    /**
     * 查询所有合同
     * @return
     */
    @Override
    public List<Contract> list() {
        return contractsMapper.list();
    }

    /**
     * 创建新的合同
     * @param contracts
     */
    @Override
    public void add(Contract contracts) {

        Double amount;
        amount = 10.0;
        //todo:结合发货单进行操作


        contracts.setContractAmount(amount);
        contracts.setFulfillmentStatus(Contract_status.CONTRACT_NON_FULFILLMENT);
        contracts.setCreateTime(LocalDateTime.now());
        contracts.setUpdateTime(LocalDateTime.now());

        contractsMapper.insert(contracts);
    }

    @Override
    public void delete(Integer contractId) {
        contractsMapper.deleteById(contractId);
    }


}
