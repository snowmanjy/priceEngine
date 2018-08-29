package com.test.app.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.app.dto.QuoteActionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActionProducer {

    @Autowired
    private ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${cloudkarafka.topic.action}")
    private String topic;

    ActionProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(QuoteActionModel quoteActionModel) throws JsonProcessingException {
        this.kafkaTemplate.send(topic, objectMapper.writeValueAsString(quoteActionModel));
    }

}