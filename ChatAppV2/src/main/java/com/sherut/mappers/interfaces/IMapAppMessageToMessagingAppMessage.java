package com.sherut.mappers.interfaces;

import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.MessageAppMessage;

public interface IMapAppMessageToMessagingAppMessage {

    MessageAppMessage map (AppMessage appMessage);
    AppMessage map (MessageAppMessage appMessage);
}
