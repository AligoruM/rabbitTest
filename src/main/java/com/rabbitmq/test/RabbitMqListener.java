package com.rabbitmq.test;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);
    Random random = new Random();


    @RabbitListener(queues = "queueTest")
    public void processQueueTest(String message) {
        logger.info("Received from queueTest: " + message);
    }

    @RabbitListener(queues = "queueTest-2")
    public void worker1(String message) throws InterruptedException {
        logger.info("worker 1 : " + message);
        Thread.sleep(100 * random.nextInt(20));
    }

    @RabbitListener(queues = "queueTest-2")
    public void worker2(String message) throws InterruptedException {
        logger.info("worker 2 : " + message);
        Thread.sleep(100 * random.nextInt(20));
    }

    @RabbitListener(queues = "exchangeTest-3-1")
    public void worker3(String message) {
        logger.info("accepted on worker 3.1 : " + message);
    }

    @RabbitListener(queues = "exchangeTest-3-2")
    public void worker4(String message) {
        logger.info("accepted on worker 3.2 : " + message);
    }

    @RabbitListener(queues = "topicTest-5")
    public void worker5(String message) {
        logger.info("accepted on worker 5 : " + message);
    }

    @RabbitListener(queues = "topicTest-6")
    public void worker6(String message) {
        logger.info("accepted on worker 6 : " + message);
    }

    @RabbitListener(queues = "topicTest-7")
    public void worker7(String message) {
        logger.info("accepted on worker 7 : " + message);
    }
}
