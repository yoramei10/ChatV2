package com.sherut.services.applicationServices.interfaces;

import com.sherut.models.ResourceModels.ChatUser;

import java.util.List;

public interface IRemoveUserApplicationService {

    public ChatUser removeUser(String id);
}
