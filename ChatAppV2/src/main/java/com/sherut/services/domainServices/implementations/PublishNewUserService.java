package com.sherut.services.domainServices.implementations;

import com.sherut.messaging.interfaces.IPublishMessage;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import com.sherut.services.domainServices.interfaces.IPublishUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class PublishNewUserService implements IPublishUserService {

    @Autowired
    private IBuildAppMessageService buildAppMessageService;
    @Autowired
    private IPublishMessage publishMessage;
    String messageContext;

    @Override
    public void publish(ChatUser newUser) {

        messageContext = String.format("added new user %s ", newUser.getName());
        AppMessage appMessage = buildAppMessageService.build(newUser, AppMessageTypeENUM.ADD_USER, messageContext);

        publishMessage.publish(appMessage);
    }
}
