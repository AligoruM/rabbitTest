package com.rabbitmq.test;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class RabbitConfiguration {

    @Autowired
    private ApplicationContext context;

    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    @Bean(name = "localConnectionFactory")
    public ConnectionFactory getConnectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin((ConnectionFactory) context.getBean("localConnectionFactory"));
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate((ConnectionFactory) context.getBean("localConnectionFactory"));
    }

    @Bean
    public Queue queueTest() {
        return new Queue("queueTest");
    }

    @Bean
    public Queue queueTest2() {
        return new Queue("queueTest-2");
    }

    @Bean
    public Queue queueTest3() {
        return new Queue("exchangeTest-3-1");
    }

    @Bean
    public Queue queueTest4() {
        return new Queue("exchangeTest-3-2");
    }

    @Bean
    public Queue queueTest5() {
        return new Queue("topicTest-5");
    }

    @Bean
    public Queue queueTest6() {
        return new Queue("topicTest-6");
    }

    @Bean
    public Queue queueTest7() {
        return new Queue("topicTest-7");
    }

    @Bean
    public FanoutExchange fanoutExchangeA() {
        return new FanoutExchange("exchangeTest-3");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicTest-4");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queueTest3()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queueTest4()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(queueTest5()).to(topicExchange()).with("*.orange.*");
    }

    @Bean
    public Binding binding4() {
        return BindingBuilder.bind(queueTest6()).to(topicExchange()).with("*.*.rabbit");
    }

    @Bean
    public Binding binding5() {
        return BindingBuilder.bind(queueTest7()).to(topicExchange()).with("lazy.#");
    }
}