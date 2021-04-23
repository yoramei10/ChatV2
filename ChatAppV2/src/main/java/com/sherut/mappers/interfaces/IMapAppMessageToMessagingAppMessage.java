package com.sherut.mappers.interfaces;

import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.ResourceDM.MessageAppMessage;

public interface IMapAppMessageToMessagingAppMessage {

    MessageAppMessage map (AppMessage appMessage);
    AppMessage map (MessageAppMessage appMessage);
}
