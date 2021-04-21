package com.sherut.services.applicationServices.implementations;

import com.sherut.models.DModels.interfaces.IAllUserDM;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.applicationServices.interfaces.IRemoveUserApplicationService;
import com.sherut.services.domainServices.interfaces.IGetAllUsersService;
import com.sherut.services.domainServices.interfaces.IRemoveUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RemoveUserApplicationService implements IRemoveUserApplicationService {

    @Autowired
    private IRemoveUserService removeUserDomainService;
    @Autowired
    IGetAllUsersService getAllUsersService;

    @Override
    public ChatUser removeUser(String id) {
        return removeUserDomainService.removeUser(getAllUsersService.getAllUsers(), id);
    }
}
