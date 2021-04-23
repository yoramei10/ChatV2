package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DM.interfaces.IAllMessagesDM;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.services.applicationServices.interfaces.IGetAllMessagesApplicationService;
import com.sherut.services.domainServices.interfaces.IGetAllUsersService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserByIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllMessagesApplicationService implements IGetAllMessagesApplicationService {

    @Autowired
    private IValidateExistUserByIDService validateExistUserByIDService;
    @Autowired
    private IAllMessagesDM allMessagesDM;
    @Autowired
    private IGetAllUsersService getAllUsersService;

    @Override
    public List<AppMessage> getALlMessages(String id) {

        if(validateExistUserByIDService.validate(getAllUsersService.getAllUsers(), id)){
            return allMessagesDM.getAllMessages();
        }
        throw new BadRequestException("wrong user");
    }
}
