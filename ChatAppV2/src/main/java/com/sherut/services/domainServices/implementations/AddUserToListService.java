package com.sherut.services.domainServices.implementations;

import com.sherut.models.DM.interfaces.IAllUserDM;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.services.domainServices.interfaces.IAddUserToListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserToListService implements IAddUserToListService {

    @Autowired
    private IAllUserDM allUser;

    @Override
    public void addUser(ChatUser chatUser) {

        allUser.addUser(chatUser);
    }
}
