package com.sherut.services.domainServices.interfaces;

import com.sherut.models.ResourceModels.ChatUser;

import java.util.List;

public interface IGetAllUsersService {

    List<ChatUser> getAllUsers();
}
