package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DModels.interfaces.IChatUserDM;
import com.sherut.models.ResourceModels.ChatUser;

import java.util.List;

public interface IValidateNewUserService {

    boolean checkNewUser(List<ChatUser> allUsers, String userName);
}

