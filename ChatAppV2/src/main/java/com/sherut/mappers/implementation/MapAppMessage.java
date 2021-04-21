package com.sherut.mappers.implementation;

import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.DModels.interfaces.IAppMessageDM;
import com.sherut.config.IFactoryDM;
import org.springframework.beans.factory.annotation.Autowired;

public class MapAppMessage implements IMapAppMessage {

    @Autowired
    private IFactoryDM factoryDM;

    @Override
    public IAppMessageDM map(AppMessage appMessage) {

        if (null == appMessage)
            return null;

        IAppMessageDM appMessageDM = factoryDM.getAppMessageDM();
        appMessageDM.setId(appMessageDM.getId());
        appMessageDM.setName(appMessageDM.getName());
        appMessageDM.setMsgContext(appMessageDM.getMsgContext());

        return appMessageDM;

    }

    @Override
    public AppMessage map(IAppMessageDM appMessageDM) {

        if (null == appMessageDM)
            return null;

        AppMessage appMessage = new AppMessage();
        appMessage.setId(appMessageDM.getId());
        appMessage.setNickName(appMessageDM.getName());
        appMessage.setMsgContext(appMessageDM.getMsgContext());

        return appMessage;
    }
}
