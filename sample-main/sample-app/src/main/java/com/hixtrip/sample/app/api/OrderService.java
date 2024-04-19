package com.hixtrip.sample.app.api;

import com.hixtrip.sample.domain.order.model.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 订单的service层
 */
public interface OrderService {

    @Transactional
    int generateOrder(Order order);
}
