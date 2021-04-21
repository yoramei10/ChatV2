package com.sherut.services.domainServices.implementations;


import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IValidateNewUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateNewUserService implements IValidateNewUserService {

    public boolean checkNewUser(List<ChatUser> allUsers, String userName) {

        return !allUsers
                .stream()
                .anyMatch(user -> userName.equalsIgnoreCase(user.getName()));
    }
}
