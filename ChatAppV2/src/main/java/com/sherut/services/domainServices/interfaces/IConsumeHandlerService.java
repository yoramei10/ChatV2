package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.ResourceDM.AppMessage;

public interface IConsumeHandlerService {

    IAppMessageDTO handleConsume(AppMessage appMessage);
}
