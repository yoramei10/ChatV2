package com.sherut.config;

import com.sherut.services.applicationServices.implementations.*;
import com.sherut.services.applicationServices.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationServiceBeanConfig {

    @Bean
    public ILoginApplicationService loginApplicationService(){
        return new LoginApplicationService();
    }

    @Bean
    public IGetAllUsersApplicationService getAllUsersApplicationService(){
        return new GetAllUsersApplicationService();
    }

    @Bean
    public IGetAllNickNamesApplicationService getAllNickNamesApplicationService(){
        return new GetAllNickNamesApplicationService();
    }

    @Bean
    public IRemoveUserApplicationService removeUserApplicationService(){
        return new RemoveUserApplicationService();
    }

    @Bean
    public IPublishMessageApplicationService publishMessageApplicationService(){
        return new PublishMessageApplicationService();
    }
}
