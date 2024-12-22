package com.company.service.impl;


import com.company.mapper.CustomerMapper;
import com.company.pojo.entity.Contract;
import com.company.pojo.entity.Customer;
import com.company.pojo.entity.PageBean;
import com.company.service.CustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询所有客户信息
     * @return
     */
    @Override
    public PageBean list(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if(id == null) {
            List<Customer> customers = customerMapper.list();
            Page<Customer> p = (Page<Customer>) customers;
            long total = p.getTotal();
            List<Customer> list = p.getResult();
            PageBean pageBean = new PageBean();
            pageBean.setTotal(total);
            pageBean.setRows(list);
            return pageBean;
        }
        else{
            Customer customer = customerMapper.findById(id);
            PageBean pageBean = new PageBean();
            pageBean.setTotal(1l); // 这里是1，因为只有一个合同
            List<Customer> customers = new ArrayList<>();
            customers.add(customer);
            pageBean.setRows(customers);
            return pageBean;
        }
    }

    /**
     * 新增客户
     * @param customer
     */
    @Override
    public void create(Customer customer) {

        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());

        customerMapper.insert(customer);
    }

    /**
     * 修改客户信息
     * @param customer
     */
    @Override
    public void modify(Customer customer) {

        customer.setUpdateTime(LocalDateTime.now());

        customerMapper.update(customer);
    }

    /**
     * 根据id删除客户
     * @param id
     */
    @Override
    public void delete(Integer id) {

        customerMapper.deleteById(id);
    }
}
