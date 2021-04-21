package com.sherut.services.domainServices.implementations;

import com.sherut.config.ConfigurationVariablesApp;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import org.springframework.beans.factory.annotation.Value;

public class BuildAppMessageService implements IBuildAppMessageService {

    private boolean MASK_ID_NAME = ConfigurationVariablesApp.MASK_ID_NAME;

    @Override
    public AppMessage build(ChatUser chatUser, Enum<AppMessageTypeENUM> type, Object messageContext) {

        AppMessage appMessage = new AppMessage();
        if (!MASK_ID_NAME){
            appMessage.setId(chatUser.getId());
            appMessage.setName(chatUser.getName());
        }
        appMessage.setNickName(chatUser.getNickName());
        appMessage.setMsgContext(messageContext);
        appMessage.setType(type.toString());
        return appMessage;
    }
}
