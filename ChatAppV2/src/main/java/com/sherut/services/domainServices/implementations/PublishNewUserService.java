package com.sherut.services.domainServices.implementations;

import com.sherut.messaging.interfaces.IPublishMessage;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IPublishNewUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class PublishNewUserService implements IPublishNewUserService {

    @Autowired
    private IPublishMessage publishMessage;

    @Override
    public void publish(ChatUser newUser) {

        AppMessage appMessage = new AppMessage();
        appMessage.setId(newUser.getId());
        appMessage.setName(newUser.getName());
        appMessage.setMsgContext(String.format("new user %s was added", newUser.getName()));

        publishMessage.publish(appMessage);
    }
}
