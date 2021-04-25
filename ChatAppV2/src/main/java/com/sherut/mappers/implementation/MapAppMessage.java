package com.sherut.mappers.implementation;

import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.models.DTO.implementations.AppMessageDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IFactoryDM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapAppMessage implements IMapAppMessage {

    @Autowired
    private IFactoryDM factoryDM;

    @Override
    public IAppMessageDTO map(AppMessage appMessage) {

        if (null == appMessage)
            return null;

        IAppMessageDTO appMessageDTO = new AppMessageDTO();
        appMessageDTO.setUserId(appMessageDTO.getUserId());
        appMessageDTO.setUserName(appMessageDTO.getUserName());
        appMessageDTO.setNickName(appMessageDTO.getNickName());

        appMessageDTO.setId(appMessageDTO.getId());
        appMessageDTO.setType(appMessageDTO.getType());
        appMessageDTO.setMsgContext(appMessageDTO.getMsgContext());

        return appMessageDTO;

    }

    @Override
    public AppMessage map(IAppMessageDTO appMessageDTO) {

        if (null == appMessageDTO)
            return null;

        AppMessage appMessage = new AppMessage();
        appMessage.setUserId(appMessageDTO.getUserId());
        appMessage.setNickName(appMessageDTO.getUserName());
        appMessage.setNickName(appMessageDTO.getNickName());

        appMessage.setId(appMessageDTO.getId());
        appMessage.setType(appMessageDTO.getType());
        appMessage.setMsgContext(appMessageDTO.getMsgContext());

        return appMessage;
    }
}
