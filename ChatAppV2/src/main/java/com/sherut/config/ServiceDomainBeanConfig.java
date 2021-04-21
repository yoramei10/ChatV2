package com.sherut.config;

import com.sherut.models.DModels.implementations.AllMessagesDM;
import com.sherut.models.DModels.implementations.FactoryDM;
import com.sherut.models.DModels.interfaces.IAllMessagesDM;
import com.sherut.services.domainServices.implementations.*;
import com.sherut.services.domainServices.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceDomainBeanConfig {


    @Bean
    public IValidateAdminService validateAdminService(){
        return new ValidateAdminService();
    }

    @Bean
    public IGetUserService getUserDomainService(){
        return new GetUserService();
    }

    @Bean
    public IRemoveUserService removeUserDomainService(){
        return new RemoveUserService();
    }

    @Bean
    public IValidateNewUserService validateNewUserService(){
        return new ValidateNewUserService();
    }

    @Bean
    public IValidateUserInputService validateUserInputService(){
        return new ValidateUserInputServiceService();
    }

    @Bean("publishNewUserService")
    public IPublishUserService publishNewUserService(){
        return new PublishNewUserService();
    }

    @Bean("publishRemoveUserService")
    public IPublishUserService publishRemoveUserService(){
        return new PublishRemoveUserService();
    }

    @Bean
    public IBuildAppMessageService buildAppMessageService(){
        return new BuildAppMessageService();
    }

    @Bean
    public IValidateExistUserByIDService validateExistUserByIDService(){
        return new ValidateExistUserByIDService();
    }

    @Bean
    public IFactoryDM factoryDM(){
        return new FactoryDM();
    }

//    @Bean
//    public IAllMessagesDM allMessagesDM(){
//        return new AllMessagesDM();
//    }

    @Bean
    public IConsumeHandlerService consumeHandlerService(){
        return new ConsumeHandlerService();
    }

}
