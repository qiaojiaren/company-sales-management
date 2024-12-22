package com.company.service;

import com.company.pojo.entity.Customer;
import com.company.pojo.entity.PageBean;

import java.util.List;

public interface CustomerService {

    /**
     * 查询所有客户
     * @return
     */
    PageBean list(Integer id, Integer pageNum, Integer pageSize);

    /**
     * 新增客户
     * @param customer
     */
    void create(Customer customer);

    /**
     * 修改客户信息
     * @param customer
     */
    void modify(Customer customer);

    /**
     * 根据id删除客户
     * @param id
     */
    void delete(Integer id);
}
