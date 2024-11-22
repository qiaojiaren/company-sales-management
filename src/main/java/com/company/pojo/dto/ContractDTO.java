package com.company.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ContractDTO {
    //客户id，和客户联系
    private Integer customerId;

    //销售人员id，和销售人员联系
    private Integer salespersonId;

    //合同描述
    private String description;

}
