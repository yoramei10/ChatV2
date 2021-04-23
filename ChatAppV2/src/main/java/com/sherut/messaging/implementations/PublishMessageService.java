package com.sherut.messaging.implementations;

import com.sherut.messaging.interfaces.IPublishMessageService;
import com.sherut.models.ResourceDM.AppMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class PublishMessageService implements IPublishMessageService {

    @Autowired
    private KafkaTemplate<String, AppMessage> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String TOPIC ;

    @Override
    public void publish(AppMessage appMessage) {
        kafkaTemplate.send(TOPIC, appMessage);

        System.out.println("publish message: " + appMessage.getMsgContext().toString() );
    }
}
