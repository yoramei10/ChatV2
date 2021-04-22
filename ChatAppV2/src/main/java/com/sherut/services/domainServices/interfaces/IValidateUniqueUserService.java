package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DModels.interfaces.IValidateDM;
import com.sherut.models.ResourceModels.ChatUser;

import java.util.List;

public interface IValidateUniqueUserService {

    IValidateDM validate (String unserName, String nickName, List<ChatUser> allUsers, IValidateDM validateDM);;
}
