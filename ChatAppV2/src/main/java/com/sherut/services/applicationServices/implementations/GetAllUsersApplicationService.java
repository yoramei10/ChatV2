package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.applicationServices.interfaces.IGetAllUsersApplicationService;
import com.sherut.services.domainServices.interfaces.IValidateAdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class GetAllUsersApplicationService implements IGetAllUsersApplicationService {

    @Autowired
    private IValidateAdminService validateAdminService;

    @Override
    public List<ChatUser> getAllUsers(String adminId, List<ChatUser> allUsers) {

        if (validateAdminService.validateAdmin(adminId)){
            return allUsers;
        }else{
            throw new BadRequestException("no permission exception");
        }
    }
}
