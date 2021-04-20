package com.sherut.services.domainServices.implementations;


import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IGetUserService;

import java.util.List;

public class GetUserService implements IGetUserService {
    @Override
    public ChatUser getUser(String id, List<ChatUser> allUsers) {

        return allUsers
                .stream()
                .filter(user -> id.equalsIgnoreCase(user.getId()))
                .findFirst()
                .orElse(null);
    }
}
