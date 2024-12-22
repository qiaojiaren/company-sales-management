package com.company.controller;

import com.company.pojo.dto.ContractDTO;
import com.company.pojo.entity.Contract;
import com.company.pojo.entity.PageBean;
import com.company.pojo.entity.Result;
import com.company.service.ContractsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 合同管理Controller
 */
@Slf4j
@RequestMapping("/contracts")
@RestController
public class ContractsController {

    @Autowired
    private ContractsService contractsService;

    /**
     * 查询合同数据
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式为GET
    @GetMapping("/search")
    public Result list(@RequestParam(value = "query",required = false) Integer id,
                       @RequestParam(value = "pagenum",required = false) Integer page,
                       @RequestParam(value = "pagesize",required = false) Integer size){
        log.info("查询合同数据");
        //调用service查询合同数据
        PageBean contractsList = contractsService.list(id,page,size);
        return Result.success(contractsList);
    }


    /**
     * 新增合同
     * @return
     */
    @PostMapping("/create")
    public Result add(@RequestBody ContractDTO contractsDTO){//传入json，参数名字和pojo相同
        log.info("新增合同: {}" , contractsDTO);
        //调用service新增合同
        contractsService.add(contractsDTO);
        return Result.success();
    }


    /**
     * 删除合同
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer contractId){
        log.info("根据id删除合同:{}",contractId);
        //调用service删除合同
        contractsService.delete(contractId);
        return Result.success();
    }

    /**
     * 合同履行
     * @param contractId
     * @return
     */
    @PutMapping("/fulfillment/{id}")
    public Result fulfillemnt(@PathVariable("id") Integer contractId){

        log.info("根据id履行合同：{}",contractId);

        contractsService.fulfillment(contractId);
        return Result.success();
    }


    /**
     * 修改合同
     * @param contract
     * @return
     */
    @PutMapping("/modify")
    public Result modify(@RequestBody Contract contract){

        log.info("修改合同：{}",contract);

        return contractsService.modify(contract) ? Result.success() : Result.error("合同已履行或者正在履行中，不能修改");

    }

    /**
     * 根据销售人员id查未履行合同
     * @param salsepersonId
     * @return
     */
    @GetMapping("/unFulfillment")
    public Result unFulfillment(@RequestParam(value = "query") Integer salsepersonId,
                                @RequestParam(value = "pagenum",required = false) Integer page,
                                @RequestParam(value = "pagesize",required = false) Integer size){

        log.info("销售人员id：{}",salsepersonId);

        PageBean contracts = contractsService.unFulfillment(salsepersonId,page,size);

        return Result.success(contracts);
    }
}
