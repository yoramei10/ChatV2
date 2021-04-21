package com.sherut.models.DModels.implementations;

import com.sherut.models.DModels.interfaces.IAppMessageDM;
import com.sherut.models.DModels.interfaces.IChatUserDM;
import com.sherut.config.IFactoryDM;
import com.sherut.models.DModels.interfaces.IValidateDM;
import org.springframework.stereotype.Service;

@Service
public class FactoryDM implements IFactoryDM {

    @Override
    public IChatUserDM getChatUserDM() {
        return new ChatUserDM();
    }

    @Override
    public IAppMessageDM getAppMessageDM() {
        return new AppMessageDM();
    }

    @Override
    public IValidateDM getValidateDM() {
        return new ValidateDM();
    }
}
