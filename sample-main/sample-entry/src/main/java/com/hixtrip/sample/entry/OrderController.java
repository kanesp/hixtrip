package com.hixtrip.sample.entry;

import com.hixtrip.sample.PaymentStatus;
import com.hixtrip.sample.app.service.OrderServiceImpl;
import com.hixtrip.sample.callback.*;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.order.model.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * todo 这是你要实现的
 */
@RestController
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    /**
     * todo 这是你要实现的接口
     *
     * @param commandOderCreateDTO 入参对象
     * @return 请修改出参对象
     */
    @PostMapping(path = "/command/order/create")
    public String order(@RequestBody CommandOderCreateDTO commandOderCreateDTO) {
        //登录信息可以在这里模拟
        long userId = 1;
        Order od = new Order();
        commandOderCreateDTO.setUserId(userId);
        BeanUtils.copyProperties(commandOderCreateDTO, od);
        int res = orderService.generateOrder(od);
        if (res > 0){
            return "Success creating this order";
        }
        return "Fail for creating oders";
    }

    /**
     * todo 这是模拟创建订单后，支付结果的回调通知
     * 【中、高级要求】需要使用策略模式处理至少三种场景：支付成功、支付失败、重复支付(自行设计回调报文进行重复判定)
     *
     * @param commandPayDTO 入参对象
     * @return 请修改出参对象
     */
    @PostMapping(path = "/command/order/pay/callback")
    public <PaymentStatus, PaymentStrategy> String payCallback(@RequestBody CommandPayDTO commandPayDTO) {
//        Order od = new Order();
//        BeanUtils.copyProperties(commandPayDTO,od);
        final Map<PaymentStatus, PaymentStrategy> strategies = new HashMap<>();
        strategies.put((PaymentStatus) com.hixtrip.sample.PaymentStatus.SUCCESS, (PaymentStrategy) new PaymentSuccessStrategyImpl());
        strategies.put((PaymentStatus) com.hixtrip.sample.PaymentStatus.FAILURE, (PaymentStrategy) new PaymentFailureStrategyImpl());
        strategies.put((PaymentStatus) com.hixtrip.sample.PaymentStatus.DUPLICATE, (PaymentStrategy) new PaymentDuplicateStrategyImpl());
        PaymentContext context = new PaymentContext(commandPayDTO);
        PaymentStatus paymentStatus = (PaymentStatus) determinePaymentStatus(commandPayDTO);
        context.setStrategy((com.hixtrip.sample.callback.PaymentStrategy) strategies.get(paymentStatus));
        context.getStrategy().process(context);
        return prepareResponse(context);

    }

    private PaymentStatus determinePaymentStatus(CommandPayDTO commandPayDTO) {
        // 实现确定支付状态（成功、失败、重复）的逻辑
        int status  = commandPayDTO.getPayStatus();
        // 根据支付状态，返回相应的 PaymentStatus 枚举值
        if(status == -1){
            return PaymentStatus.FAILURE;
        } else if (status == 2) {
            return PaymentStatus.DUPLICATE;
        } else if (status == 1) {
            return PaymentStatus.SUCCESS;
        }
        return PaymentStatus.FAILURE;
    }

    private String prepareResponse(PaymentContext context) {
        // Extract relevant information from the PaymentContext
        PaymentStatus paymentStatus = PaymentStatus.fromCode(context.getCommandPayDTO().getPayStatus());
        int orderId = context.getOrder().getId();
        BigDecimal amount = context.getOrder().getPayAmount();

        // Construct the response payload based on the payment status
        switch (paymentStatus) {
            case SUCCESS:
                return Response.prepareSuccessResponse(orderId, amount); // Construct a success response
            case FAILURE:
                return Response.prepareFailureResponse(orderId, amount); // Construct a failure response
            case DUPLICATE:
                return Response.prepareDuplicateResponse(orderId, amount); // Construct a duplicate payment response
        }
        return Response.prepareFailureResponse(orderId, amount);
    }



}
