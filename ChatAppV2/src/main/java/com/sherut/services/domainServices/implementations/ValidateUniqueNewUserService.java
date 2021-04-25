package com.sherut.services.domainServices.implementations;

import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.domainServices.interfaces.IValidateUniqueUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidateUniqueNewUserService implements IValidateUniqueUserService {


    @Autowired
    private IUserRepository userRepository;

    @Override
    public IValidateDM validate(IChatUserDTO chatUser, IValidateDM validateDM) {

        String userName = chatUser.getUserName();
        String nickName  = chatUser.getNickName();

        if (null != userRepository.getByUserName(userName)){
            validateDM.setValue(false);
            validateDM.setValidateMessage(validateDM.getValidateMessage() + " user name already exist");
        }

        if (StringUtils.hasText(nickName)){
            if (null != userRepository.getByNickName(nickName)){
                validateDM.setValue(false);
                validateDM.setValidateMessage(validateDM.getValidateMessage() + " nick name already exist");
            }

        }else{
            if (null != userRepository.getByNickName(userName)){
                validateDM.setValue(false);
                validateDM.setValidateMessage(validateDM.getValidateMessage() + " nick name already exist");
            }
        }

        return validateDM;
    }
}
