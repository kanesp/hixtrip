package com.hixtrip.sample.callback;

public class PaymentDuplicateStrategyImpl implements PaymentStrategy {

    @Override
    public void process(PaymentContext context) {
        // 记录重复支付事件
        logDuplicatePaymentEvent(Math.toIntExact(context.getOrder().getId()));

        // 向客户发送重复支付通知（可选）
         sendDuplicatePaymentNotification(Math.toIntExact(context.getOrder().getUserId()));

        // 执行重复支付所需的任何其他操作
    }

    private void logDuplicatePaymentEvent(int orderId) {
        // 实现记录重复支付事件的逻辑
    }

    private void sendDuplicatePaymentNotification(int userId) {
        // 实现发送重复支付通知的逻辑（可选）
        System.out.println(userId+" has paid the order multiply.");
    }
}
