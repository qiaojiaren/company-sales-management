package com.company.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPays {

    private Integer customerId;

    private String customerName;

    //客户销售总额
    private Double pays;

}
