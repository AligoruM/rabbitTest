package com.rabbitmq.test;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
    Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    RabbitTemplate template;

    @RequestMapping("/emit")
    @ResponseBody
    String queueTest() {
        logger.info("Emit to QueueTest");
        template.convertAndSend("queueTest", "Message to queue");
        return "Emit to queue";
    }

    @RequestMapping("/queue")
    @ResponseBody
    String queueTest2() {
        logger.info("Emit to queue");
        for (int i = 0; i < 10; i++)
            template.convertAndSend("queueTest-2", "Message " + i);
        return "Emit to queue";
    }

    @RequestMapping("/exchange")
    @ResponseBody
    String queueTest3() {
        logger.info("Emit to exchangeTest-3");
        template.setExchange("exchangeTest-3");
        template.convertAndSend("Fanout message");
        return "Emit to exchangeTest-3";
    }

    @RequestMapping("/routing/{key}/{message}")
    @ResponseBody
    String topicTest(@PathVariable("key") String key, @PathVariable("message") String message) {
        logger.info(String.format("Emit '%s' to '%s'", message, key));
        template.setExchange("topicTest-4");
        template.convertAndSend(key, message);
        return String.format("Emit '%s' to '%s'", message, key);
    }
}
