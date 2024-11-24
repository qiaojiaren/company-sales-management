package com.company.controller;

import com.company.pojo.dto.ContractDTO;
import com.company.pojo.entity.Contract;
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

    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private ContractsService contractsService;

    /**
     * 查询部门数据
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式为GET
    @GetMapping("/search")
    public Result list(){
        log.info("查询全部合同数据");
        //调用service查询合同数据
        List<Contract> contractsList = contractsService.list();
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
    @DeleteMapping("/delete")
    public Result delete(Integer contractId){
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
    @PutMapping("/fulfillment")
    public Result fulfillemnt(Integer contractId){

        log.info("根据id履行合同：{}",contractId);

        contractsService.fulfillment(contractId);
        return Result.success();
    }

}
