package com.sherut.services.applicationServices.interfaces;

import com.sherut.models.ResourceDM.ChatUser;

import java.util.List;

public interface IGetAllUsersApplicationService {

    List<ChatUser> getAllUsers (String adminId);
}
