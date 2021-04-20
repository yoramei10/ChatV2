package com.sherut.config;

import com.sherut.messaging.implementations.PublishMessage;
import com.sherut.messaging.interfaces.IPublishMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public IPublishMessage publishMessage(){
        return new PublishMessage();
    }
}
