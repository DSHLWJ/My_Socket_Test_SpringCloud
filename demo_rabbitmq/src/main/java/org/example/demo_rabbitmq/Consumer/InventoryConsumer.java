package org.example.demo_rabbitmq.Consumer;

import org.example.demo_rabbitmq.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * *@ClassName InverntoryConsumer
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/18 13:52
 * *Version 1.0
 **/
@Component
public class InventoryConsumer {

    //private final InventoryService inventoryService;
    //
    //public InventoryConsumer(InventoryService inventoryService) {
    //    this.inventoryService = inventoryService;
    //}

    //使用 @RabbitListener 支持多个队列 支持监听多个
    //@RabbitListener(queues = {"queue1", "queue2", "queue3"})
    @RabbitListener(queues ={ "orderQueue", "testQueue"})
    public void handleOrder(String msg) {
    //public void handleOrder(Map<String, Object> msg) {
        System.out.println("库存系统收到订单消息: " + msg);

        //String productId = (String) msg.get("productId");
        //int quantity = (int) msg.get("quantity");

        // 这里做库存扣减
        //inventoryService.deductStock(productId, quantity);
    }
}
