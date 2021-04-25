package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;

public interface IPublishMessageService {

    void publish (IAppMessageDTO messageDTO);
}
