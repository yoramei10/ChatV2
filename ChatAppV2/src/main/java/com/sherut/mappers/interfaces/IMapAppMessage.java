package com.sherut.mappers.interfaces;

import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;

public interface IMapAppMessage {

    IAppMessageDTO map (AppMessage appMessage);
    AppMessage map (IAppMessageDTO appMessageDM);
}
