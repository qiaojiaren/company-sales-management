package com.company.service.impl;

import com.company.constant.Contract_status;
import com.company.mapper.ContractsMapper;
import com.company.pojo.Contract;
import com.company.service.ContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContractsServiceImpl implements ContractsService {

    @Autowired
    private ContractsMapper contractsMapper;

    //todo:自动注入产品表，用于计算合同金额

    @Override
    public List<Contract> list() {
        return contractsMapper.list();
    }

    @Override
    public void add(Contract contracts) {

        Double amount;
        //todo: amount=contracts.getProductQuantity()*
        amount= 10.00;

//        contracts.setCustomerId(contracts.getCustomerId());
//        contracts.setProductId(contracts.getProductId());
//        contracts.setSalespersonId(contracts.getSalespersonId());
//        contracts.setProductQuantity(contracts.getProductQuantity());
        contracts.setContractAmount(amount);
//        contracts.setContractContent(contracts.getContractContent());
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
