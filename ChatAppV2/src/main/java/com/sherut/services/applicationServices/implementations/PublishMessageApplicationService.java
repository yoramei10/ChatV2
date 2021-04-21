package com.sherut.services.applicationServices.implementations;

import com.sherut.config.ConfigurationVariablesApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.applicationServices.interfaces.IPublishMessageApplicationService;
import com.sherut.messaging.interfaces.IPublishMessageService;
import com.sherut.services.domainServices.interfaces.IGetAllUsersService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserByIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class PublishMessageApplicationService implements IPublishMessageApplicationService {

    @Autowired
    private IPublishMessageService publishMessage;
    @Autowired
    private IValidateExistUserByIDService validateExistUserByIDService;
    @Autowired
    private IGetAllUsersService getAllUsersService;
    @Value("${MASK_ID_NAME}")
    private boolean MASK_ID_NAME;


    @Override
    public void publish(String userId, AppMessage appMessage) {

        if (validateExistUserByIDService.validate(getAllUsersService.getAllUsers(), userId)) {
            appMessage.setType(AppMessageTypeENUM.MESSAGE.name());

            if (MASK_ID_NAME){
                appMessage.setName(null);
                appMessage.setId(null);
            }
            publishMessage.publish(appMessage);
        }else{
            throw new BadRequestException("wrong user");
        }
    }
}
