package com.hixtrip.sample.domain.order;


import com.hixtrip.sample.domain.RedisService;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.repository.OrderRepository;
import com.hixtrip.sample.domain.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    RedisService redisService;
    @Override
    public int createOrder(Order order) {
        String key = "redis:order:" + order.getSkuId();
        if (!redisService.hasKey(key)){
            redisService.set(key, 100);
        }

        int cache = (int) redisService.get(key);
        if (cache < order.getAmount()){
            System.out.println("Fails in that: " + order.getSkuId() +" has no storages.");
            return 0;
        }
        int res = orderMapper.insertOrder(order);
        cache -= order.getAmount();
        redisService.set(key,cache);
        System.out.println("Success creating this oder.");
        return res;
    }
}
