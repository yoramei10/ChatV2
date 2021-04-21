package com.sherut.config;

import com.sherut.models.DModels.interfaces.IAllMessagesDM;
import com.sherut.models.DModels.interfaces.IAppMessageDM;
import com.sherut.models.DModels.interfaces.IChatUserDM;
import com.sherut.models.DModels.interfaces.IValidateDM;

public interface IFactoryDM {

    IChatUserDM getChatUserDM();
    IAppMessageDM getAppMessageDM();
    IValidateDM getValidateDM();
}
