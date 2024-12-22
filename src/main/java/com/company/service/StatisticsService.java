package com.company.service;


import com.company.pojo.entity.CustomerPays;
import com.company.pojo.entity.Performance;
import com.company.pojo.entity.TotalSales;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {

    /**
     * 销售总额统计
     * @param
     * @return
     */
    TotalSales totalSales(LocalDateTime begin, LocalDateTime end);

    /**
     * 区间内客户销售
     * @param begin
     * @param end
     * @return
     */
    List<CustomerPays> customerPays(LocalDateTime begin, LocalDateTime end);

    /**
     * 查业绩
     * @param begin
     * @param end
     * @param id
     * @return
     */
    Performance performance(LocalDateTime begin, LocalDateTime end, Integer id);
}
