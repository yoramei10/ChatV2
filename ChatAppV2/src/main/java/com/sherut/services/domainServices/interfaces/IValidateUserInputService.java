package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DModels.interfaces.IValidateDM;

public interface IValidateUserInputService {

    IValidateDM validate (String userName, String password);
}
