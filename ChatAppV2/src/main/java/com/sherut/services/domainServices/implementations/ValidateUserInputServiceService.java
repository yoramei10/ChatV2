package com.sherut.services.domainServices.implementations;

import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.services.domainServices.interfaces.IValidateUserInputService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidateUserInputServiceService implements IValidateUserInputService {



    @Override
    public IValidateDM validate(IChatUserDTO chatUser, IValidateDM validateDM) {

        String userName = chatUser.getUserName();
        String password = chatUser.getPassword();
        String nickName  = chatUser.getNickName();

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
