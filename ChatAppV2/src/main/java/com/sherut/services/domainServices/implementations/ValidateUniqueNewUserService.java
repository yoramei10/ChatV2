package com.sherut.services.domainServices.implementations;

import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.domainServices.interfaces.IIsUniqueService;
import com.sherut.services.domainServices.interfaces.IValidateUniqueUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidateUniqueNewUserService implements IValidateUniqueUserService {

    @Autowired
    @Qualifier("IsUniqueUserName")
    private IIsUniqueService isUniqueUserNameService;
    @Autowired
    @Qualifier("IsUniqueNickName")
    private IIsUniqueService isUniqueNickNameService;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public IValidateDM validate(IChatUserDTO chatUser, IValidateDM validateDM) {

        String userName = chatUser.getUserName();
        String nickName  = chatUser.getNickName();

//        List<IChatUserDTO> allUsers = userRepository.findAll();

        if (null != userRepository.getByUserName(userName)){
            validateDM.setValue(false);
            validateDM.setValidateMessage(validateDM.getValidateMessage() + " user name already exist");
        }
//       if(!isUniqueUserNameService.isUnique(allUsers, userName)){
//            validateDM.setValue(false);
//            validateDM.setValidateMessage(validateDM.getValidateMessage() + " user name already exist");
//        }




        if (StringUtils.hasText(nickName)){
            if (null != userRepository.getByNickName(nickName)){
                validateDM.setValue(false);
                validateDM.setValidateMessage(validateDM.getValidateMessage() + " nick name already exist");
            }

//            if(!isUniqueNickNameService.isUnique(allUsers, nickName)){
//                validateDM.setValue(false);
//                validateDM.setValidateMessage(validateDM.getValidateMessage() + " nick name already exist");
//            }
        }else{
            if (null != userRepository.getByNickName(userName)){
                validateDM.setValue(false);
                validateDM.setValidateMessage(validateDM.getValidateMessage() + " nick name already exist");
            }
//            if(!isUniqueNickNameService.isUnique(allUsers, userName)){
//                validateDM.setValue(false);
//                validateDM.setValidateMessage(validateDM.getValidateMessage() + " name already exist");
//            }
        }

        return validateDM;
    }
}
