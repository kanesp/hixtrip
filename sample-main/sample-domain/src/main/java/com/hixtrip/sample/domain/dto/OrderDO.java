package com.hixtrip.sample.domain.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "order", autoResultMap = true)
@SuperBuilder(toBuilder = true)
public class OrderDO implements Serializable {

    @TableId
    private Long id;

    private String skuId;

    private Long userId;

    private String orderSn;

    private Integer amount;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private Integer status;

    private BigDecimal totalAmount;

    private BigDecimal payAmount;

    private Integer payType;

    private LocalDateTime payTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

