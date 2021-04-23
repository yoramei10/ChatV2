package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.ResourceDM.ChatUser;

import java.util.List;

public interface IValidateUniqueUserService {

    IValidateDM validate (ChatUser chatUser, List<ChatUser> allUsers, IValidateDM validateDM);;
}
