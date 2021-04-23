package com.sherut.models.DTO.implementations;

import com.sherut.models.DM.implementations.ValidateDM;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.DTO.interfaces.IFactoryDTO;
import com.sherut.models.DM.interfaces.IValidateDM;
import org.springframework.stereotype.Service;

@Service
public class FactoryDTO implements IFactoryDTO {

    @Override
    public IChatUserDTO getChatUserDTO() {
        return new ChatUserDTO();
    }

    @Override
    public IAppMessageDTO getAppMessageDTO() {
        return new AppMessageDTO();
    }

    @Override
    public IValidateDM getValidateDTO() {
        return new ValidateDM();
    }
}
