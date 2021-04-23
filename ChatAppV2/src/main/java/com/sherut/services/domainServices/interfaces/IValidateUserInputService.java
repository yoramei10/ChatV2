package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.ResourceDM.ChatUser;

public interface IValidateUserInputService {

    IValidateDM validate (ChatUser chatUser, IValidateDM validateDM);
}
