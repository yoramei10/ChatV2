package com.sherut.services.domainServices.implementations;

import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.ResourceDM.ChatUser;
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
    public IValidateDM validate(ChatUser chatUser, List<ChatUser> allUsers, IValidateDM validateDM) {

        String userName = chatUser.getName();
        String nickName  = chatUser.getNickName();

        if(!isUniqueUserNameService.isUnique(allUsers, userName)){
            validateDM.setValue(false);
            validateDM.setValidateMessage(validateDM.getValidateMessage() + " user name already exist");
        }
        if (StringUtils.hasText(nickName)){
            if(!isUniqueNickNameService.isUnique(allUsers, nickName)){
                validateDM.setValue(false);
                validateDM.setValidateMessage(validateDM.getValidateMessage() + " nick name already exist");
            }
        }else{
            if(!isUniqueNickNameService.isUnique(allUsers, userName)){
                validateDM.setValue(false);
                validateDM.setValidateMessage(validateDM.getValidateMessage() + " name already exist");
            }
        }

        return validateDM;
    }
}
