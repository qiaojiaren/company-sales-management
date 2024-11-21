package com.company.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 合同实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private Integer contractId; //合同id
    private Integer customerId; //客户id，和客户联系
    private Integer salespersonId;//销售人员id，和销售人员联系
    private Double contractAmount;//合同金额
    private String contractContent;//合同内容
    private String fulfillmentStatus;//合同实现状态
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

}
