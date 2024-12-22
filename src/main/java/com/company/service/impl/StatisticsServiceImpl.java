package com.company.service.impl;

import com.company.mapper.StatisticsMapper;
import com.company.pojo.entity.CustomerPays;
import com.company.pojo.entity.Performance;
import com.company.pojo.entity.ProductSales;
import com.company.pojo.entity.TotalSales;
import com.company.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 区间内销售总额
     * @param begin
     * @param end
     * @return
     */
    @Override
    public TotalSales totalSales(LocalDateTime begin, LocalDateTime end) {

        TotalSales totalSales = new TotalSales();
        //得到总额
        totalSales.setAmount(statisticsMapper.totalSales(begin,end));
        //得到每个商品名字和销售
        totalSales.setProductSales(statisticsMapper.productSales(begin,end));

        return totalSales;
    }

    /**
     * 区间内客户销售
     * @param begin
     * @param end
     * @return
     */
    @Override
    public List<CustomerPays> customerPays(LocalDateTime begin, LocalDateTime end) {

        List<CustomerPays> customerPays= statisticsMapper.customerPays(begin,end);
        return customerPays;
    }

    /**
     * 查业绩
     * @param begin
     * @param end
     * @param id
     * @return
     */
    @Override
    public Performance performance(LocalDateTime begin, LocalDateTime end, Integer id) {

        Performance performance = statisticsMapper.performance(begin,end,id);
        return performance;
    }
}
