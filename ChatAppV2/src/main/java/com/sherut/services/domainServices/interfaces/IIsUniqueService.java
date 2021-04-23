package com.sherut.services.domainServices.interfaces;

import com.sherut.models.ResourceDM.ChatUser;

import java.util.List;

public interface IIsUniqueService {

    boolean isUnique(List<ChatUser> allUsers, String userName);
}

