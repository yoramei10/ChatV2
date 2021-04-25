package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DTO.interfaces.IChatUserDTO;


public interface IValidateExistUserService {

    IChatUserDTO validate(String id);
}
