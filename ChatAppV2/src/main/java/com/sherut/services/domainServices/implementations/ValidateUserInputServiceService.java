package com.sherut.services.domainServices.implementations;

import com.sherut.config.IFactoryDM;
import com.sherut.models.DModels.interfaces.IValidateDM;
import com.sherut.services.domainServices.interfaces.IValidateUserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidateUserInputServiceService implements IValidateUserInputService {



    @Override
    public IValidateDM validate(String userName, String password, String nickName, IValidateDM validateDM) {

        if (!StringUtils.hasText(userName) || userName.length() < 3) {
            validateDM.setValue(false);
            validateDM.setValidateMessage(validateDM.getValidateMessage() + "not valid user name");
        }

        if (!StringUtils.hasText(password) || password.length() < 3) {
            validateDM.setValue(false);
            validateDM.setValidateMessage(validateDM.getValidateMessage() + ", not valid password");
        }

        if (StringUtils.hasText(nickName) && nickName.length() < 3) {
            validateDM.setValue(false);
            validateDM.setValidateMessage(validateDM.getValidateMessage() + ", not valid nick name");
        }
        return validateDM;
    }
}
