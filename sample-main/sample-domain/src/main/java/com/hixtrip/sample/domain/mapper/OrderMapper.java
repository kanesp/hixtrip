package com.hixtrip.sample.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.dto.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<OrderDO> {
    int insertOrder( Order order);
}

