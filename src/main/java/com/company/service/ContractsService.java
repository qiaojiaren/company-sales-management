package com.company.service;

import com.company.pojo.dto.ContractDTO;
import com.company.pojo.entity.Contract;

import java.util.List;

/**
 * 合同管理
 */
public interface ContractsService {
    /**
     * 查询全部合同信息
     * @return
     */
    List<Contract> list();

    /**
     * 新增合同
     * @param contractsDTO
     */
    void add(ContractDTO contractsDTO);


    /**
     * 删除合同
     * @param contractId
     */
    void delete(Integer contractId);

    /**
     * 履行合同
     * @param contractId
     */
    void fulfillment(Integer contractId);
}
