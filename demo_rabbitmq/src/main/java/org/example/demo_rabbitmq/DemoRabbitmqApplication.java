package org.example.demo_rabbitmq;

import org.example.demo_rabbitmq.Service.Producer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoRabbitmqApplication implements CommandLineRunner {


    /**
     * 消费订阅机制
     * 使用rabbitmq存储信息，给其他系统或者模块使用或者消费，增强系统之间的解耦能力
     *
     * 一个作为生产者，比如产生订单信息
     * 一个作为消费者，比如库存系统，处理订单，增减库存
     * 当库存系统崩溃也不影响下单量，持续增减库存，，当库存系统恢复后处理积攒的订单信息即可
     * **/

    private final Producer producer;


    public DemoRabbitmqApplication(Producer producer) {
        this.producer = producer;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoRabbitmqApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 100; i++) {
            producer.send("Hello RabbitMQ!");
        }
        //producer.send("Hello RabbitMQ!");
    }
}
