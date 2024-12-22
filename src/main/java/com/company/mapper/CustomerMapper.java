package com.company.mapper;

import com.company.pojo.entity.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface CustomerMapper {

    /**
     * 查询所有客户信息
     * @return
     */
    @Select("select * from customers")
    List<Customer> list();

    /**
     * 新增客户信息
     * @param customer
     */
    void insert(Customer customer);

    /**
     * 修改客户信息
     * @param customer
     */
    void update(Customer customer);

    /**
     * 根据id删除客户信息
     * @param id
     */
    @Delete("delete from customers where customer_id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据id查询客户信息
     * @param id
     * @return
     */
    @Select("select * from customers where customer_id = #{id}")
    Customer findById(Integer id);
}
