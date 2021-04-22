package com.sherut.services.domainServices.implementations;


import com.sherut.exceptions.EntityNotFoundException;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IPublishUserService;
import com.sherut.services.domainServices.interfaces.IRemoveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoveUserService implements IRemoveUserService {

    @Autowired
    @Qualifier("publishRemoveUserService")
    private IPublishUserService publishRemoveMessage;

    @Override
    public ChatUser removeUser(List<ChatUser> allUsers, String id) {

        ChatUser chatUserToRemove = allUsers.stream().filter(user -> id.equalsIgnoreCase(user.getId())).findFirst().orElse(null);

        if (null != chatUserToRemove) {
            allUsers.remove(chatUserToRemove);

            publishRemoveMessage.publish(chatUserToRemove);

            return chatUserToRemove;

        } else {
            throw new EntityNotFoundException("fail remove user. not found");
        }
    }
}
