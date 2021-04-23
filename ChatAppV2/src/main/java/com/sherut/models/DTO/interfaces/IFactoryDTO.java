package com.sherut.models.DTO.interfaces;

import com.sherut.models.DM.interfaces.IValidateDM;

public interface IFactoryDTO {

    IChatUserDTO getChatUserDTO();
    IAppMessageDTO getAppMessageDTO();
    IValidateDM getValidateDTO();
}
