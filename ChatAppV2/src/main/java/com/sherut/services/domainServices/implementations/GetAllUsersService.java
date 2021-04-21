package com.sherut.services.domainServices.implementations;

import com.sherut.models.DModels.interfaces.IAllUserDM;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IGetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersService implements IGetAllUsersService {

    // TODO: implement in DB ?
    @Autowired
    private IAllUserDM allUser;

    @Override
    public List<ChatUser> getAllUsers() {
        return allUser.getAllUsers();
    }
}
