package com.sherut.services.domainServices.interfaces;

import com.sherut.models.ResourceDM.ChatUser;

import java.util.List;

public interface IRemoveUserService {

    public ChatUser removeUser(List<ChatUser> allUsers, String id);
}
