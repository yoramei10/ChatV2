package com.sherut.services.domainServices.implementations;

import com.sherut.messaging.interfaces.IPublishMessageService;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import com.sherut.services.domainServices.interfaces.IPublishUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublishNewUserService implements IPublishUserService {

    @Autowired
    private IBuildAppMessageService buildAppMessageService;
    @Autowired
    private IPublishMessageService publishMessage;
    @Value("${appMessage.messageContext.addNewUser}")
    String messageContext;

    @Override
    public void publish(ChatUser newUser) {

        AppMessage appMessage = buildAppMessageService.build(newUser, AppMessageTypeENUM.ADD_USER, messageContext + newUser.getName());

        publishMessage.publish(appMessage);
    }
}
