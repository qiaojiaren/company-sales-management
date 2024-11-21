package com.company.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 库存实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private Integer productId;//产品id
    private String productName;//产品名称
    private Integer quantity;//产品库存数量
    private Double sellPrice;//售出价格
    private Double purchasePrice;//采购价格
    private Integer threshold;//警戒阈值
    private LocalDateTime createTime;//创建日期
    private LocalDateTime updateTime;//更新日期
}
