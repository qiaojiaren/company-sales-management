package com.company.service;

import com.company.pojo.dto.ContractDTO;
import com.company.pojo.entity.Contract;
import com.company.pojo.entity.PageBean;

import java.util.List;

/**
 * 合同管理
 */
public interface ContractsService {
    /**
     * 查询全部合同信息
     * @return
     */
    PageBean list(Integer id, Integer pageNum, Integer pageSize);

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

    /**
     * 修改合同
     * @param contract
     */
    boolean modify(Contract contract);

    /**
     * 根据销售人员id查未履行合同
     * @param salsepersonId
     * @return
     */
    PageBean unFulfillment(Integer salsepersonId,Integer pageNum, Integer pageSize);
}
