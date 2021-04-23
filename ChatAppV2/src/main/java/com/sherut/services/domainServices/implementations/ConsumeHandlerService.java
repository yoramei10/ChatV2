package com.sherut.services.domainServices.implementations;

import com.sherut.models.DM.interfaces.IAllMessagesDM;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.services.domainServices.interfaces.IConsumeHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumeHandlerService implements IConsumeHandlerService {

    @Autowired
    private IAllMessagesDM allMessagesDM;

    @Override
    public IAllMessagesDM handleConsume(AppMessage appMessage) {

        allMessagesDM.addMessage(appMessage);
        return allMessagesDM;
    }
}
