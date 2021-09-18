package com.ntnn.cucumber.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "services.rabbitmq")
public class RabbitMqProperties {
    private String host;
    private String port;
    private String username;
    private String password;
    private Integer concurrentMin;
    private Integer concurrentMax;
    private List<String> queues;
}
