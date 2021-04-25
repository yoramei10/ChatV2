package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;

public interface IBuildAppMessageService {

    IAppMessageDTO build (IChatUserDTO chatUser, AppMessageTypeENUM type, Object messageContext);
}
