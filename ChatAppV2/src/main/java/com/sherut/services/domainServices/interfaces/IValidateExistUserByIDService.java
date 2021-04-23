package com.sherut.services.domainServices.interfaces;

import com.sherut.models.ResourceDM.ChatUser;

import java.util.List;

public interface IValidateExistUserByIDService {

    boolean validate(List<ChatUser> allUsers, String id);
}
