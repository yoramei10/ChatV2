package com.sherut.services.applicationServices.implementations;

import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.applicationServices.interfaces.IPublishMessageApplicationService;
import com.sherut.messaging.interfaces.IPublishMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PublishMessageApplicationService implements IPublishMessageApplicationService {

    @Autowired
    private IPublishMessage publishMessage;

    @Override
    public void publish(List<ChatUser> allUsers, String userId, AppMessage appMessage) {

        publishMessage.publish(appMessage);
    }
}
