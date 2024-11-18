package com.company.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 库存实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {//todo:先完善产品表，然后再对合同操作进行编写
    private Integer productId;//产品id
    private String productName;//产品名称
    private Integer quantity;//产品库存数量
    private Double sellPrice;//售出价格
    private Double purchasePrice;//采购价格
    private Integer threshold;//警戒阈值
    private LocalDateTime createTime;//创建日期
    private LocalDateTime updateTime;//更新日期
}
