package org.example.demo_rabbitmq.Consumer;

import org.example.demo_rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * *@ClassName Consumer
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/18 12:52
 * *Version 1.0
 **/

/**
 * 同一个 @RabbitListener 队列只能被一个消费者方法绑定
 * 在 Spring Boot 中，@RabbitListener 是通过 SimpleMessageListenerContainer 创建监听容器
 * 如果多个方法监听同一个队列，Spring Boot 默认只会 注册第一个，后面的会被忽略或抛异常（有时候会被覆盖）
 * 所以 consumer1 和 consumer2 同时监听 testQueue，只有一个真正生效
 * **/
/**
 * 单个队列消息分发规则
 * 一个队列的每条消息只能被一个消费者消费
 * 即使你有多个消费者监听同一个队列，RabbitMQ 也只会把一条消息发送给其中一个消费者
 * 轮询分发 的前提是：
 * 队列里有多条消息
 * 多个消费者同时监听同一个队列
 * 每条消息 只会被其中一个消费者接收，不会广播到所有消费者
 * **/
@Component
public class Consumer {
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receive(String msg) {
        System.out.println("收到消息: " + msg);
    }
}
