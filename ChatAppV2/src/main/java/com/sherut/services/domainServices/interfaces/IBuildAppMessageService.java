package com.sherut.services.domainServices.interfaces;

import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;

public interface IBuildAppMessageService {

    AppMessage build (ChatUser chatUser, Enum<AppMessageTypeENUM> type, Object messageContext);
}
