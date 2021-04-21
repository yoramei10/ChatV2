package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.applicationServices.interfaces.IPublishMessageApplicationService;
import com.sherut.messaging.interfaces.IPublishMessage;
import com.sherut.services.domainServices.interfaces.IValidateExistUserByIDService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PublishMessageApplicationService implements IPublishMessageApplicationService {

    @Autowired
    private IPublishMessage publishMessage;
    @Autowired
    private IValidateExistUserByIDService validateExistUserByIDService;


    @Override
    public void publish(List<ChatUser> allUsers, String userId, AppMessage appMessage) {

        if (validateExistUserByIDService.validate(allUsers, userId)) {
            appMessage.setType(AppMessageTypeENUM.MESSAGE.name());
            publishMessage.publish(appMessage);
        }else{
            throw new BadRequestException("wrong user");
        }
    }
}
