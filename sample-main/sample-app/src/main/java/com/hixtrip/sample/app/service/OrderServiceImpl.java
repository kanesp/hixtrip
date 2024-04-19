package com.hixtrip.sample.app.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.order.model.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * app层负责处理request请求，调用领域服务
 */
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDomainService orderDomainService;
    @Override
    public int generateOrder(Order order) {
        Order od = new Order();
        BeanUtils.copyProperties(order,od);
        return orderDomainService.createOrder(od);
    }
}
