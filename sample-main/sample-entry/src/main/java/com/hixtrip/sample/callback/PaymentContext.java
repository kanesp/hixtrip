package com.hixtrip.sample.callback;

import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.order.model.Order;

public class PaymentContext {

    private final CommandPayDTO commandPayDTO;
    private Order order;
    private PaymentStrategy strategy;

    public PaymentContext(CommandPayDTO commandPayDTO) {
        this.commandPayDTO = commandPayDTO;
        this.order = new Order(); // 初始化订单对象
    }

    public CommandPayDTO getCommandPayDTO() {
        return commandPayDTO;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
}

