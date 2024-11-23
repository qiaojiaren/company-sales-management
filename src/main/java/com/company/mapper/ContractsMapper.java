package com.company.mapper;

import com.company.pojo.entity.Contract;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 合同管理
 */
@Mapper
public interface ContractsMapper {
    /**
     * 查询全部合同
     * @return
     */
    @Select("select * from contracts")
    List<Contract> list();

    /**
     * 找最新的一条合同
     * @return
     */
    @Select("select * from contracts order by contract_id desc limit 1")
    Contract findLatest();

    /**
     * 新增合同
     * @param contract
     */
    void insert(Contract contract);



    /**
     * 根据ID删除合同
     * @param contractId
     */
    @Delete("delete from contracts where contract_id = #{contractId}")
    void deleteById(Integer contractId);

}
