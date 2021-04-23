package com.sherut.services.applicationServices.interfaces;

import com.sherut.models.ResourceDM.AppMessage;

import java.util.List;

public interface IGetAllMessagesApplicationService {

    List<AppMessage> getALlMessages(String id);
}
