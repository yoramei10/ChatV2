package com.sherut.services.domainServices.implementations;


import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IIsUniqueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IsUniqueUserName")
public class IsUniqueUserNameService implements IIsUniqueService {

    public boolean isUnique(List<ChatUser> allUsers, String userName) {

        return !allUsers
                .stream()
                .anyMatch(user -> userName.equalsIgnoreCase(user.getName()));
    }
}
