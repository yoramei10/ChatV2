package com.sherut.services.domainServices.implementations;

import com.sherut.config.IFactoryDM;
import com.sherut.models.DModels.interfaces.IValidateDM;
import com.sherut.services.domainServices.interfaces.IValidateUserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class ValidateUserInputServiceService implements IValidateUserInputService {

    @Autowired
    private IFactoryDM factoryDM;

    @Override
    public IValidateDM validate(String userName, String password) {

        IValidateDM validateDM = factoryDM.getValidateDM();
        validateDM.setValue(true);
        validateDM.setValidateMessage("");

        if (!StringUtils.hasText(userName) || userName.length() < 3) {
            validateDM.setValue(false);
            validateDM.setValidateMessage("not valid userName, ");
        }

        if (!StringUtils.hasText(password) || password.length() < 3) {
            validateDM.setValue(false);
            validateDM.setValidateMessage(validateDM.getValidateMessage() + "not valid password");
        }
        return validateDM;
    }
}
