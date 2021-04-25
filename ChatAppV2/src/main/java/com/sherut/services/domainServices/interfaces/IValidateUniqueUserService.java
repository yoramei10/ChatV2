package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.DTO.interfaces.IChatUserDTO;

import java.util.List;

public interface IValidateUniqueUserService {

    IValidateDM validate (IChatUserDTO chatUser, IValidateDM validateDM);;
}
