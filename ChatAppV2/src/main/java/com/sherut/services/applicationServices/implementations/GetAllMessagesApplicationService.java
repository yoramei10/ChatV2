package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DModels.interfaces.IAllMessagesDM;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.applicationServices.interfaces.IGetAllMessagesApplicationService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserByIDService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetAllMessagesApplicationService implements IGetAllMessagesApplicationService {

    @Autowired
    private IValidateExistUserByIDService validateExistUserByIDService;
    @Autowired
    private IAllMessagesDM allMessagesDM;

    @Override
    public List<AppMessage> getALlMessages(List<ChatUser> allUsers, String id) {

        if(validateExistUserByIDService.validate(allUsers, id)){
            return allMessagesDM.getAllMessages();
        }
        throw new BadRequestException("wrong user");
    }
}
