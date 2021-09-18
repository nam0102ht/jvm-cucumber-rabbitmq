package com.ntnn.cucumber.service;

import com.ntnn.cucumber.config.RabbitMqProperties;
import com.ntnn.cucumber.repository.model.CommonModel;
import com.ntnn.cucumber.repository.mq.MainRepository;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitListenMessage {
    @Autowired
    private MainRepository mainRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMqProperties rabbitMqProperties;

    @RabbitListener(queues = "${services.rabbitmq.incomingQueue}")
    public void processMessage(String commonModel, Channel channel) {
        String messageBase64 = mainRepository.processBase64(commonModel);
        rabbitTemplate.convertAndSend(rabbitMqProperties.getQueues().get(1), messageBase64);
    }
}
