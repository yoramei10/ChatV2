package com.sherut.messaging.implementations;

import com.sherut.messaging.interfaces.IListenerMessageService;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.services.domainServices.interfaces.IConsumeHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

public class ListenerMessageService implements IListenerMessageService {

    @Value("${spring.kafka.topic}")
    private String TOPIC ;

    @Autowired
    private IConsumeHandlerService consumeHandlerService;

    @Override
    @KafkaListener(topics = "ChatTopic", groupId = "group_id")
    public void consume(AppMessage message) {

        consumeHandlerService.handleConsume(message);
    }
}
