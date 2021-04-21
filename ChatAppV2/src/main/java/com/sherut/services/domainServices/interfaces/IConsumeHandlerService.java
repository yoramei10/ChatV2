package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DModels.interfaces.IAllMessagesDM;
import com.sherut.models.ResourceModels.AppMessage;

public interface IConsumeHandlerService {

    IAllMessagesDM handleConsume(AppMessage appMessage);
}
