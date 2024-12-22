package com.company.controller;

import com.company.pojo.entity.*;
import com.company.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequestMapping("/statistics")
@RestController
public class StatisticsController {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    private StatisticsService statisticsService;

    /**
     *销售总额统计
     * @return
     */
    @GetMapping("/totalSales")
    public Result totalSales(@RequestParam String beginDate,@RequestParam String endDate){


        log.info("统计区间：{}");

        LocalDate beginD = LocalDate.parse(beginDate, formatter);
        LocalDate endD = LocalDate.parse(endDate, formatter);

        LocalDateTime begin = beginD.atStartOfDay();
        LocalDateTime end = endD.atTime(23,59,59);


        TotalSales totalSales = statisticsService.totalSales(begin,end);

        return Result.success(totalSales);
    }

    /**
     *顾客销售总额统计
     * @return
     */
    @GetMapping("/customerPays")
    public Result customerPays(@RequestParam("begin") String beginDate,@RequestParam("end") String endDate){


        log.info("统计区间：{}");

        LocalDate beginD = LocalDate.parse(beginDate, formatter);
        LocalDate endD = LocalDate.parse(endDate, formatter);

        LocalDateTime begin = beginD.atStartOfDay();
        LocalDateTime end = endD.atTime(23,59,59);


        List<CustomerPays> customerPays = statisticsService.customerPays(begin,end);
        return Result.success(customerPays);
    }

    /**
     * 销售表现
     * @param beginDate
     * @param endDate
     * @param id
     * @return
     */
    @GetMapping("/performance")
    public Result performance(@RequestParam("begin") String beginDate,
                              @RequestParam("end") String endDate,@RequestParam("id") Integer id){

        log.info("统计区间：{}");

        LocalDate beginD = LocalDate.parse(beginDate, formatter);
        LocalDate endD = LocalDate.parse(endDate, formatter);

        LocalDateTime begin = beginD.atStartOfDay();
        LocalDateTime end = endD.atTime(23,59,59);


        Performance performance = statisticsService.performance(begin,end,id);
        return Result.success(performance);
    }

}
