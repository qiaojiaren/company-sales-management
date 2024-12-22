package com.company.service;

import com.company.pojo.dto.ContractDTO;
import com.company.pojo.entity.Contract;
import com.company.pojo.entity.Customer;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.Salesperson;

import java.util.List;

public interface SalespersonService {

    /**
     * 查询所有销售人员
     * @return
     */
    PageBean list(Integer id, Integer pageNum, Integer pageSize);

    /**
     * 新增销售人员
     * @param salesperson
     */
    void create(Salesperson salesperson);

    /**
     * 修改销售人员信息
     * @param salesperson
     */
    void modify(Salesperson salesperson);

    /**
     * 根据id删除销售人员
     * @param id
     */
    void delete(Integer id);

    /**
     * 员工登陆接口
     * @param salesperson
     * @return
     */
    Salesperson login(Salesperson salesperson);
}
