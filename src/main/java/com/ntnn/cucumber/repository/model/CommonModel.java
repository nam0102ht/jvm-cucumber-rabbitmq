package com.ntnn.cucumber.repository.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude
public class CommonModel implements Serializable {
    private String id;
    private String title;
    private String message;


    public CorrelationData getCorrelationData() {
        return new CorrelationData(id + "|" + title);
    }
}
