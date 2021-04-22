package com.sherut.services.domainServices.implementations;

import com.sherut.models.DModels.interfaces.IValidateDM;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IIsUniqueService;
import com.sherut.services.domainServices.interfaces.IValidateUniqueUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ValidateUniqueNewUserService implements IValidateUniqueUserService {

    @Autowired
    @Qualifier("IsUniqueUserName")
    private IIsUniqueService isUniqueUserNameService;
    @Autowired
    @Qualifier("IsUniqueNickName")
    private IIsUniqueService isUniqueNickNameService;

    @Override
    public IValidateDM validate(String userName, String nickName,List<ChatUser> allUsers, IValidateDM validateDM) {

        if(!isUniqueUserNameService.isUnique(allUsers, userName)){
            validateDM.setValue(false);
            validateDM.setValidateMessage(validateDM.getValidateMessage() + " not valid user name");
        }
        if (StringUtils.hasText(nickName)){
            if(!isUniqueNickNameService.isUnique(allUsers, nickName)){
                validateDM.setValue(false);
                validateDM.setValidateMessage(validateDM.getValidateMessage() + " not valid nick name");
            }
        }else{
            if(!isUniqueNickNameService.isUnique(allUsers, userName)){
                validateDM.setValue(false);
                validateDM.setValidateMessage(validateDM.getValidateMessage() + " not valid name");
            }
        }

        return validateDM;
    }
}