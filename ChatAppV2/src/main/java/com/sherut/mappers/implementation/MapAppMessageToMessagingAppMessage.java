package com.sherut.mappers.implementation;

import com.sherut.mappers.interfaces.IMapAppMessageToMessagingAppMessage;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.MessageAppMessage;

public class MapAppMessageToMessagingAppMessage implements IMapAppMessageToMessagingAppMessage {

    @Override
    public MessageAppMessage map(AppMessage appMessage) {

        if (null == appMessage)
            return null;

        MessageAppMessage messageAppMessage = new MessageAppMessage();
        messageAppMessage.setId(appMessage.getId());
        messageAppMessage.setName(appMessage.getNickName());
        messageAppMessage.setMsgContext(appMessage.getMsgContext());

        return messageAppMessage;

    }

    @Override
    public AppMessage map(MessageAppMessage messageAppMessage) {

        if (null == messageAppMessage)
            return null;

        AppMessage appMessage = new AppMessage();
        appMessage.setId(messageAppMessage.getId());
        appMessage.setNickName(messageAppMessage.getName());
        appMessage.setMsgContext(messageAppMessage.getMsgContext());

        return appMessage;

    }
}
