package org.example.demo_rabbitmq.config;

import org.example.demo_rabbitmq.utils.SpringContext;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * *@ClassName RabbitConfig
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/18 12:46
 * *Version 1.0
 **/
@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "testQueue";
    public static final String EXCHANGE_NAME = "testExchange";
    public static final String ROUTING_KEY = "testKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true); // 持久化队列
    }

    @Bean
    public Queue orderQueue() {
        return new Queue("orderQueue", true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }


    /**
     * 批量创建队列跟交换机、key的方法
     *
     * **/
    //private static final String EXCHANGE_NAME = "testExchange";
    //
    //// 队列名和 routing key 列表
    //private static final String[][] QUEUES = {
    //        {"queue1", "key1"},
    //        {"queue2", "key2"},
    //        {"queue3", "key3"}
    //};

    //@Bean
    //public DirectExchange exchange() {
    //    return new DirectExchange(EXCHANGE_NAME);
    //}
    //
    //// 批量创建队列和绑定
    //@Bean
    //public List<Queue> queues(DirectExchange exchange) {
    //    List<Queue> queueList = new ArrayList<>();
    //    for (String[] q : QUEUES) {
    //        String queueName = q[0];
    //        String routingKey = q[1];
    //        Queue queue = new Queue(queueName, true);
    //        queueList.add(queue);
    //
    //        // 绑定队列到交换机
    //        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
    //        // 通过 BeanFactory 注册 Binding Bean
    //        SpringContext.registerBean(queueName + "Binding", binding);
    //    }
    //    return queueList;
    //}







}

