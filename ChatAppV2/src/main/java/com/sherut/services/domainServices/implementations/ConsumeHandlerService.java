package com.sherut.services.domainServices.implementations;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.services.domainServices.interfaces.IConsumeHandlerService;
import org.springframework.stereotype.Service;

@Service
public class ConsumeHandlerService implements IConsumeHandlerService {


    @Override
    public IAppMessageDTO handleConsume(AppMessage appMessage) {

        return null;
    }
}
