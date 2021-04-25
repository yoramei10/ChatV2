package com.sherut.models.DTO.implementations;

import com.sherut.models.DM.implementations.ValidateDM;
import com.sherut.models.DTO.interfaces.IFactoryDM;
import com.sherut.models.DM.interfaces.IValidateDM;
import org.springframework.stereotype.Service;

@Service
public class FactoryDM implements IFactoryDM {


    @Override
    public IValidateDM getValidateDM() {
        return new ValidateDM();
    }
}
