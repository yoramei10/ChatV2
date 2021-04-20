package com.sherut.mappers.interfaces;

import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.DModels.interfaces.IAppMessageDM;

public interface IMapAppMessage {

    IAppMessageDM map (AppMessage appMessage);
    AppMessage map (IAppMessageDM appMessageDM);
}
