package com.sherut.mappers.implementation;

import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IFactoryDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class MapAppMessage implements IMapAppMessage {

    @Autowired
    private IFactoryDTO factoryDM;

    @Override
    public IAppMessageDTO map(AppMessage appMessage) {

        if (null == appMessage)
            return null;

        IAppMessageDTO appMessageDM = factoryDM.getAppMessageDTO();
        appMessageDM.setId(appMessageDM.getId());
        appMessageDM.setName(appMessageDM.getName());
        appMessageDM.setMsgContext(appMessageDM.getMsgContext());

        return appMessageDM;

    }

    @Override
    public AppMessage map(IAppMessageDTO appMessageDM) {

        if (null == appMessageDM)
            return null;

        AppMessage appMessage = new AppMessage();
        appMessage.setId(appMessageDM.getId());
        appMessage.setNickName(appMessageDM.getName());
        appMessage.setMsgContext(appMessageDM.getMsgContext());

        return appMessage;
    }
}
