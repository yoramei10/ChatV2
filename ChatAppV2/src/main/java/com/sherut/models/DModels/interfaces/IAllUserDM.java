package com.sherut.models.DModels.interfaces;

import com.sherut.models.ResourceModels.ChatUser;

import java.util.List;

public interface IAllUserDM {

    List<ChatUser> getAllUsers();

    void addUser(ChatUser user);
}
