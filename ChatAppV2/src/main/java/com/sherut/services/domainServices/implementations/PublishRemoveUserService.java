package com.sherut.services.domainServices.implementations;

import com.sherut.messaging.interfaces.IPublishMessage;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import com.sherut.services.domainServices.interfaces.IPublishUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class PublishRemoveUserService implements IPublishUserService {

    @Autowired
    private IBuildAppMessageService buildAppMessageService;
    @Autowired
    private IPublishMessage publishMessage;
    String messageContext;

    @Override
    public void publish(ChatUser user) {

        messageContext = String.format("remove user %s ", user.getName());
        AppMessage appMessage = buildAppMessageService.build(user, AppMessageTypeENUM.REMOVE_USER, messageContext);

        publishMessage.publish(appMessage);
    }
}
