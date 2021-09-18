package com.ntnn.cucumber.main;

import com.ntnn.cucumber.ComponentTest;
import com.ntnn.cucumber.config.RabbitMqProperties;
import com.ntnn.cucumber.repository.model.CommonModel;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@ComponentTest
public class MainTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RabbitMqProperties rabbitMqProperties;
    private final static CountDownLatch waiter = new CountDownLatch(1);

    @SneakyThrows
    @Test
    public void firstTest() {

        rabbitTemplate.convertAndSend(rabbitMqProperties.getQueues().get(0), "Hello");

        waiter.await(1000, TimeUnit.MILLISECONDS);

        String message = (String) rabbitTemplate.receiveAndConvert(rabbitMqProperties.getQueues().get(1));
        Assert.assertEquals("SGVsbG8=", message);
    }
}
