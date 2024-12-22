package com.company.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {

    private Integer salespersonId;

    private String salespersonName;

    //业绩
    private Double performance;
}
