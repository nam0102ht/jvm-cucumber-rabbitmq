package com.ntnn.cucumber.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
@Configuration
public class RabbitMqConfiguration {
    private final RabbitMqProperties rabbitMqProperties;
    private final Environment environment;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(rabbitMqProperties.getConcurrentMin());
        factory.setMaxConcurrentConsumers(rabbitMqProperties.getConcurrentMax());
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        for(String profile: environment.getActiveProfiles()){
            if(profile.equals("consumer-manual-ack")){
                factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
                break;
            }
        }
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean("queueInput")
    public Queue queueInput() {
        return new Queue(rabbitMqProperties.getQueues().get(0), true, false, false);
    }

    @Bean("queueOutput")
    public Queue queueOutput() {
        return new Queue(rabbitMqProperties.getQueues().get(1), true, false, false);
    }

}
