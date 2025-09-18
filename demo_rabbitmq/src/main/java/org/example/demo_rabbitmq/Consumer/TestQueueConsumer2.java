package org.example.demo_rabbitmq.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * *@ClassName TestQueueConsumer2
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/18 15:33
 * *Version 1.0
 **/
@Component
public class TestQueueConsumer2 {
    @RabbitListener(queues = "testQueue")
    public void consumer2(String msg) {
        System.out.println("consumer2收到: " + msg);
    }
}