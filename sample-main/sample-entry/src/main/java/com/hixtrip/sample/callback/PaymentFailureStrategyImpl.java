package com.hixtrip.sample.callback;

public class PaymentFailureStrategyImpl implements PaymentStrategy {

    @Override
    public void process(PaymentContext context) {
        // 更新订单状态为“付款失败”
        context.getOrder().setStatus(-1);

        // 向客户发送付款失败通知
        sendPaymentFailureNotification(Math.toIntExact(context.getOrder().getUserId()));

        // 执行支付失败所需的任何其他操作
    }

    private void sendPaymentFailureNotification(int userId) {
        // 实现发送付款失败通知的逻辑
        System.out.println(userId+" has failed the order.");

    }
}
