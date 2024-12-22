package com.company.service.impl;

import com.company.mapper.SalespersonMapper;
import com.company.pojo.entity.Customer;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.Salesperson;
import com.company.service.SalespersonService;
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
public class SalespersonServiceImpl implements SalespersonService {
    @Autowired
    private SalespersonMapper salespersonMapper;

    /**
     * 查询所有信息
     * @return
     */
    @Override
    public PageBean list(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if(id == null) {
            List<Salesperson> salespersons = salespersonMapper.list();
            Page<Salesperson> p = (Page<Salesperson>) salespersons;
            long total = p.getTotal();
            List<Salesperson> list = p.getResult();
            PageBean pageBean = new PageBean();
            pageBean.setTotal(total);
            pageBean.setRows(list);
            return pageBean;
        }
        else{
            Salesperson salesperson = salespersonMapper.findById(id);
            PageBean pageBean = new PageBean();
            pageBean.setTotal(1l); // 这里是1，因为只有一个合同
            List<Salesperson> salespersons = new ArrayList<>();
            salespersons.add(salesperson);
            pageBean.setRows(salespersons);
            return pageBean;
        }
    }

    /**
     * 新增
     * @param salesperson
     */
    @Override
    public void create(Salesperson salesperson) {

        salesperson.setCreateTime(LocalDateTime.now());
        salesperson.setUpdateTime(LocalDateTime.now());

        salespersonMapper.insert(salesperson);
    }

    /**
     * 修改信息
     * @param salesperson
     */
    @Override
    public void modify(Salesperson salesperson) {

        salesperson.setUpdateTime(LocalDateTime.now());

        salespersonMapper.update(salesperson);
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void delete(Integer id) {

        salespersonMapper.deleteById(id);
    }

    /**
     * 员工登陆
     * @param salesperson
     * @return
     */
    @Override
    public Salesperson login(Salesperson salesperson) {
        return salespersonMapper.getByUsernameAndPassword(salesperson);
    }
}
