package com.sherut.services.domainServices.implementations;

import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.messaging.interfaces.IPublishMessageGWService;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.services.domainServices.interfaces.IPublishMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class
PublishMessageService implements IPublishMessageService {

    @Autowired
    private IPublishMessageGWService publishMessageGWService;
    @Autowired
    private IMapAppMessage mapAppMessage;


    @Override
    public void publish(IAppMessageDTO appMessageDTO) {

        AppMessage appMessage = mapAppMessage.map(appMessageDTO);

        publishMessageGWService.publish(appMessage);
    }
}
