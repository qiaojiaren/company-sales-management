package com.company.controller;

import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.Salesperson;
import com.company.pojo.entity.Result;
import com.company.service.SalespersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/salesperson")
@RestController
public class SalespersonController {

    @Autowired
    private SalespersonService salespersonService;

    @GetMapping("/list")
    public Result list(@RequestParam(value = "query",required = false) Integer id,
                       @RequestParam(value = "pagenum",required = false) Integer page,
                       @RequestParam(value = "pagesize",required = false) Integer size){

        log.info("查询所有销售人员信息");

        PageBean salespersons = salespersonService.list(id,page,size);

        return Result.success(salespersons);
    }

    /**
     * 新增客户信息
     * @return
     */
    @PostMapping("/create")
    public Result create(@RequestBody Salesperson salesperson){

        log.info("新增销售人员：{}",salesperson);

        salespersonService.create(salesperson);
        return Result.success();
    }

    /**
     * 修改
     * @param salesperson
     * @return
     */
    @PutMapping("/modify")
    public Result modify(@RequestBody Salesperson salesperson){

        log.info("修改销售人员信息：{}",salesperson);

        salespersonService.modify(salesperson);
        return Result.success();
    }

    /**
     * 根据id删除销售人员
     * @param salespersonId
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer salespersonId){

        log.info("根据id删除销售人员：{}",salespersonId);

        salespersonService.delete(salespersonId);
        return Result.success();
    }
}
