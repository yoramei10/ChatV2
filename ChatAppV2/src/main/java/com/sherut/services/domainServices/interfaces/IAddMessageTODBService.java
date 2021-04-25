package com.sherut.services.domainServices.interfaces;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.enums.AppMessageTypeENUM;

public interface IAddMessageTODBService {

    IAppMessageDTO add(IChatUserDTO userDTO, AppMessageTypeENUM type, Object messageContext);
}
