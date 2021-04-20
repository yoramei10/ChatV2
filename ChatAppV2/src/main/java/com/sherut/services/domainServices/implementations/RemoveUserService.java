package com.sherut.services.domainServices.implementations;


import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IRemoveUserService;

import java.util.List;

public class RemoveUserService implements IRemoveUserService {

    @Override
    public ChatUser removeUser(List<ChatUser> allUsers, String id) {

        ChatUser chatUserToRemove = allUsers.stream().filter(user -> id.equalsIgnoreCase(user.getId())).findFirst().orElse(null);

        if (null != chatUserToRemove) {
            allUsers.remove(chatUserToRemove);
            return chatUserToRemove;
        } else {
            throw new BadRequestException("fail remove user");
        }
    }
}
