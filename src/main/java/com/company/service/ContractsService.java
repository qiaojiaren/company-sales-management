package com.company.service;

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
     * @param contracts
     */
    void add(Contract contracts);


    /**
     * 删除合同
     * @param contractId
     */
    void delete(Integer contractId);

}
