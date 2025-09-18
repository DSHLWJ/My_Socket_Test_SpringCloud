package org.example.demo_rabbitmq.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * *@ClassName TestQueueConsumer1
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/18 15:33
 * *Version 1.0
 **/
@Component
public class TestQueueConsumer1 {
    @RabbitListener(queues = "testQueue")
    public void consumer1(String msg) {
        System.out.println("consumer1收到: " + msg);
    }
}
