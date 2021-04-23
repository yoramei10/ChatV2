package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DM.interfaces.IAllMessagesDM;
import com.sherut.models.ResourceDM.AppMessage;

public interface IConsumeHandlerService {

    IAllMessagesDM handleConsume(AppMessage appMessage);
}
