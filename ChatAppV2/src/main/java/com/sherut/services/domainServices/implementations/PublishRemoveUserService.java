package com.sherut.services.domainServices.implementations;

import com.sherut.messaging.interfaces.IPublishMessage;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import com.sherut.services.domainServices.interfaces.IPublishUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class PublishRemoveUserService implements IPublishUserService {

    @Autowired
    private IBuildAppMessageService buildAppMessageService;
    @Autowired
    private IPublishMessage publishMessage;
    @Value("${appMessage.messageContext.removeUser}")
    String messageContext;

    @Override
    public void publish(ChatUser user) {

        AppMessage appMessage = buildAppMessageService.build(user, AppMessageTypeENUM.REMOVE_USER, messageContext + user.getName());

        publishMessage.publish(appMessage);
    }
}