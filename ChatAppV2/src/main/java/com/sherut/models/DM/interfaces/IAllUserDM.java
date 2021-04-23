package com.sherut.models.DM.interfaces;

import com.sherut.models.ResourceDM.ChatUser;

import java.util.List;

public interface IAllUserDM {

    List<ChatUser> getAllUsers();

    void addUser(ChatUser user);
}
