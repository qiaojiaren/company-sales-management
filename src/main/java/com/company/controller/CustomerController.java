package com.company.controller;

import com.company.mapper.CustomerMapper;
import com.company.pojo.entity.Customer;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.Result;
import com.company.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public Result list(@RequestParam(value = "query",required = false) Integer id,
                       @RequestParam(value = "pagenum",required = false) Integer page,
                       @RequestParam(value = "pagesize",required = false) Integer size){

        log.info("查询所有客户信息");

        PageBean customers = customerService.list(id,page,size);

        return Result.success(customers);
    }

    /**
     * 新增客户信息
     * @return
     */
    @PostMapping("/create")
    public Result create(@RequestBody Customer customer){

        log.info("新增客户：{}",customer);

        customerService.create(customer);
        return Result.success();
    }

    /**
     * 修改客户信息
     * @param customer
     * @return
     */
    @PutMapping("/modify")
    public Result modify(@RequestBody Customer customer){

        log.info("修改客户信息：{}",customer);

        customerService.modify(customer);
        return Result.success();
    }

    /**
     * 根据id删除客户
     * @param customerId
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable(value = "id") Integer customerId){

        log.info("根据id删除客户：{}",customerId);

        customerService.delete(customerId);
        return Result.success();
    }
}
