package com.company.mapper;

import com.company.pojo.entity.Contract;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("select * from contracts where contract_id = #{contractId}")
    Contract findById(Integer contractId);

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

    /**
     * 根据id履行合同
     * @param contract
     */
    @Update("update contracts set fulfillment_status = #{fulfillmentStatus} where contract_id = #{contractId}")
    void fulfillmentById(Contract contract);


    /**
     * 修改合同
     */
    @Update("update contracts set customer_id = #{customerId}, salesperson_id = #{salespersonId}," +
            "contract_amount = #{contractAmount}, contract_content = #{contractContent}," +
            "description = #{description}, fulfillment_status = #{fulfillmentStatus}," +
            "update_time = #{updateTime} where contract_id = #{contractId}" )
    void modifyById(Contract contract);

    /**
     * 根据销售人员id查未履行合同
     * @param salespersonId
     * @return
     */
    @Select("select * from contracts where salesperson_id = #{salespersonId} and fulfillment_status = #{unFulfill}")
    List<Contract> unFulfillment(Integer salespersonId,String unFulfill);
}
