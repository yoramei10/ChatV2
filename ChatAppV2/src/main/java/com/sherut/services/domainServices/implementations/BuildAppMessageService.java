package com.sherut.services.domainServices.implementations;

import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BuildAppMessageService implements IBuildAppMessageService {

    @Value("${MASK_ID_NAME}")
    private boolean MASK_ID_NAME;

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
