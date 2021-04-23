package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.services.applicationServices.interfaces.IGetAllUsersApplicationService;
import com.sherut.services.domainServices.interfaces.IGetAllUsersService;
import com.sherut.services.domainServices.interfaces.IValidateAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersApplicationService implements IGetAllUsersApplicationService {

    @Autowired
    private IValidateAdminService validateAdminService;
    @Autowired
    private IGetAllUsersService getAllUsersService;

    @Override
    public List<ChatUser> getAllUsers(String adminId) {

        if (validateAdminService.validateAdmin(adminId)){
            return getAllUsersService.getAllUsers();
        }else{
            throw new BadRequestException("no permission exception");
        }
    }
}
