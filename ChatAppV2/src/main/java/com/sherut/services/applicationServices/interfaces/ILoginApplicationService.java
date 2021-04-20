package com.sherut.services.applicationServices.interfaces;

import com.sherut.models.ResourceModels.ChatUser;

import java.util.List;

public interface ILoginApplicationService {

    public ChatUser loginApp (List<ChatUser> allUsers, String userName, String password, String nickName);
}
