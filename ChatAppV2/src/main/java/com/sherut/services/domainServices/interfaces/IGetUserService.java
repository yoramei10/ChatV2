package com.sherut.services.domainServices.interfaces;

import com.sherut.models.ResourceDM.ChatUser;

import java.util.List;

public interface IGetUserService {

    ChatUser getUser (String id, List<ChatUser> allUsers);
}
