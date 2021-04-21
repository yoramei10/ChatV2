package com.sherut.messaging.interfaces;

import com.sherut.models.ResourceModels.AppMessage;
import org.springframework.kafka.annotation.KafkaListener;

public interface IListenerMessageService {

    void consume(AppMessage message);
}
