package com.sherut.services.domainServices.implementations;

import com.sherut.models.DTO.implementations.AppMessageDTO;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BuildAppMessageService implements IBuildAppMessageService {

    @Value("${MASK_ID_NAME}")
    private boolean MASK_ID_NAME;


    @Override
    public IAppMessageDTO build(IChatUserDTO chatUser, AppMessageTypeENUM type, Object messageContext) {

        IAppMessageDTO appMessage = new AppMessageDTO();
        if (!MASK_ID_NAME){
            appMessage.setUserId(chatUser.getId());
            appMessage.setUserName(chatUser.getUserName());
        }
        appMessage.setNickName(chatUser.getNickName());
        appMessage.setMsgContext(messageContext);
        appMessage.setType(type);

        return appMessage;
    }
}
