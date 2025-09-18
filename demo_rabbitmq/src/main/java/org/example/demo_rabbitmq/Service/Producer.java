package org.example.demo_rabbitmq.Service;

import org.example.demo_rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * *@ClassName Producer
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/18 12:51
 * *Version 1.0
 **/
@Service
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String msg) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY,
                msg
        );
        System.out.println("消息已发送: " + msg);
    }
}

