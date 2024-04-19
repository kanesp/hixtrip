package com.hixtrip.sample.callback;

public class PaymentSuccessStrategyImpl implements PaymentStrategy {

    @Override
    public void process(PaymentContext context) {
        // 更新订单状态为“已支付”
        context.getOrder().setStatus(1);

        // 向客户发送付款成功通知
        sendPaymentSuccessNotification(context.getOrder().getUserId());

        // 执行支付成功所需的任何其他操作
    }

    private void sendPaymentSuccessNotification(int userId) {
        // 实现发送付款成功通知的逻辑
        System.out.println(userId+" has paid the order successfully, and waiting for receiving the packages.");
    }
}
