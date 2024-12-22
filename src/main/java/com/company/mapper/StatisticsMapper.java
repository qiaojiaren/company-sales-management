package com.company.mapper;

import com.company.pojo.entity.CustomerPays;
import com.company.pojo.entity.Performance;
import com.company.pojo.entity.ProductSales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StatisticsMapper {


    /**
     * 统计一段时间销售总额
     * @param begin
     * @param end
     * @return
     */
    @Select("select sum(contract_amount) as amount from contracts where create_time between #{begin} and #{end}")
    Double totalSales(LocalDateTime begin, LocalDateTime end);

    /**
     * 统计每个商品的销售
     * @param begin
     * @param end
     * @return
     */
    @Select("select i.product_id, i.product_name, sum(d.product_quantity) as quantity, i.sell_price * sum(d.product_quantity) as sales " +
            "from inventory i, delivery_orders d " +
            "where i.product_id = d.product_id and d.create_time between #{begin} and #{end} group by i.product_id")
    List<ProductSales> productSales(LocalDateTime begin, LocalDateTime end);

    /**
     * 区间内客户销售总额
     * @param begin
     * @param end
     * @return
     */
    @Select("select c1.customer_id, customer_name, sum(contract_amount) as pays from contracts c1,customers c2 " +
            "where c1.customer_id = c2.customer_id and c1.create_time between #{begin} and #{end} " +
            "group by c2.customer_id")
    List<CustomerPays> customerPays(LocalDateTime begin, LocalDateTime end);

    /**
     * 查业绩
     * @param begin
     * @param end
     * @param id
     * @return
     */
    @Select("select s.salesperson_id, salesperson_name, sum(contract_amount) as performance " +
            "from salespersons s, contracts c where s.salesperson_id = c.salesperson_id " +
            "and c.create_time between #{begin} and #{end} and s.salesperson_id = #{id}")
    Performance performance(LocalDateTime begin, LocalDateTime end, Integer id);
}
