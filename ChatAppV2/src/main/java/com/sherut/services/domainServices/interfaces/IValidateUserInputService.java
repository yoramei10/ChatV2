package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.DTO.interfaces.IChatUserDTO;

public interface IValidateUserInputService {

    IValidateDM validate (IChatUserDTO chatUser, IValidateDM validateDM);
}
