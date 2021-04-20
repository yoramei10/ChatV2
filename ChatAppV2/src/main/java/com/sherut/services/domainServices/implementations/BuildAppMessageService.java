package com.sherut.services.domainServices.implementations;

import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;

public class BuildAppMessageService implements IBuildAppMessageService {

    @Override
    public AppMessage build(ChatUser chatUser, Enum<AppMessageTypeENUM> type, Object messageContext) {

        AppMessage appMessage = new AppMessage();
        appMessage.setId(chatUser.getId());
        appMessage.setName(chatUser.getName());
        appMessage.setMsgContext(messageContext);
        appMessage.setType(type.toString());
        return appMessage;
    }
}
