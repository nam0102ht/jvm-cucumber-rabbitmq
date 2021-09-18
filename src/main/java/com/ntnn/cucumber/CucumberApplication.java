package com.ntnn.cucumber;

import com.ntnn.cucumber.config.RabbitMqProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties({RabbitMqProperties.class})
@PropertySource("classpath:application.yml")
public class CucumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CucumberApplication.class, args);
    }


}
