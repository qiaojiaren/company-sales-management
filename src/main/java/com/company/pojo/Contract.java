package com.company.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 合同实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private Integer contractId; //合同id
    private Integer customerId; //客户id，和客户联系
    private String productId; //产品id，和库存联系
    private Integer salespersonId;//销售人员id，和销售人员联系
    private Integer productQuantity;//销售产品数量
    private Double contractAmount;//合同金额
    private String contractContent;//合同内容
    private String fulfillmentStatus;//合同实现状态
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

}
