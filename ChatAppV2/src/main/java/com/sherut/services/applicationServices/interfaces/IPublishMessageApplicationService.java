package com.sherut.services.applicationServices.interfaces;

import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;

import java.util.List;

public interface IPublishMessageApplicationService {

    public void publish (String userId, AppMessage appMessage);
}
