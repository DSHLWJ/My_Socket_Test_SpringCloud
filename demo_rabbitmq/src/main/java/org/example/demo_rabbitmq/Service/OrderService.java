package org.example.demo_rabbitmq.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * *@ClassName OrderService
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/18 13:52
 * *Version 1.0
 **/
@Service
public class OrderService {

    //private final RabbitTemplate rabbitTemplate;
    //
    //
    //public OrderService(RabbitTemplate rabbitTemplate) {
    //    this.rabbitTemplate = rabbitTemplate;
    //}
    //
    //public String createOrder(String userId, String productId, int quantity) {
    //    // 1. 写订单数据库
    //    //Order order = new Order(userId, productId, quantity);
    //    //orderRepository.save(order);
    //
    //    // 2. 发送消息到 MQ
    //    Map<String, Object> msg = new HashMap<>();
    //    //msg.put("orderId", order.getId());
    //    msg.put("userId", userId);
    //    msg.put("productId", productId);
    //    msg.put("quantity", quantity);
    //
    //    rabbitTemplate.convertAndSend(
    //            "orderExchange",
    //            "orderKey",
    //            msg
    //    );
    //
    //    // 3. 返回下单成功
    //    //return "订单创建成功，订单号：" + order.getId();
    //    return "订单创建成功，订单号：";
    //}
}

