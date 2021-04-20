package com.sherut.config;

import com.sherut.mappers.implementation.MapAppMessage;
import com.sherut.mappers.implementation.MapAppMessageToMessagingAppMessage;
import com.sherut.mappers.implementation.MapChatUserToChatUserDM;
import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.mappers.interfaces.IMapAppMessageToMessagingAppMessage;
import com.sherut.mappers.interfaces.IMapChatUserToChatUserDM;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperBeanConfig {

    @Bean
    public IMapChatUserToChatUserDM mapChatUserToChatUserDM(){
        return new MapChatUserToChatUserDM();
    }

    @Bean
    public IMapAppMessage mapAppMessage(){
        return new MapAppMessage();
    }

    @Bean
    public IMapAppMessageToMessagingAppMessage mapAppMessageToMessagingAppMessage(){
        return new MapAppMessageToMessagingAppMessage();
    }
}
