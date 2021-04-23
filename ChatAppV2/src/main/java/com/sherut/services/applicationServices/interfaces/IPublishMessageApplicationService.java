package com.sherut.services.applicationServices.interfaces;

import com.sherut.models.ResourceDM.AppMessage;

public interface IPublishMessageApplicationService {

    public void publish (String userId, AppMessage appMessage);
}
