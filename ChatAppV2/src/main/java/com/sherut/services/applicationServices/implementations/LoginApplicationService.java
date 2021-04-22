package com.sherut.services.applicationServices.implementations;

import com.sherut.config.IFactoryDM;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DModels.interfaces.IValidateDM;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.applicationServices.interfaces.ILoginApplicationService;
import com.sherut.config.ConfigurationVariablesApp;
import com.sherut.services.domainServices.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoginApplicationService implements ILoginApplicationService {

    @Autowired
    private IValidateUserInputService validateUserInputService;
    @Autowired
    @Qualifier("publishNewUserService")
    private IPublishUserService publishNewUserService;
    @Autowired
    private IGetAllUsersService getAllUsersService;
    @Autowired
    private IAddUserToListService adUserService;
    @Autowired
    private IFactoryDM factoryDM;
    @Autowired
    private IValidateUniqueUserService validateUniqueUserService;


    AtomicInteger id = new AtomicInteger();
    String USER_PREF = ConfigurationVariablesApp.userPref;

    public ChatUser loginApp (String userName, String password, String nickName) {

        IValidateDM validateDM = factoryDM.getValidateDM();
        validateDM.setValue(true);
        validateDM.setValidateMessage("");

        validateDM = validateUserInputService.validate(userName, password, nickName, validateDM);
        if (!validateDM.getValue()) {
            throw new BadRequestException(validateDM.getValidateMessage());
        }

        validateDM = validateUniqueUserService.validate(userName, nickName, getAllUsersService.getAllUsers(), validateDM);
        if (!validateDM.getValue()) {
            throw new BadRequestException(validateDM.getValidateMessage());
        } else {

            ChatUser user = new ChatUser();
            user.setName(userName);
            user.setId(USER_PREF + userName + "_" + id.incrementAndGet());
            user.setPassword(password);

            if (null == nickName || nickName.length() == 0) {
                user.setNickName(userName);
            } else {
                user.setNickName(nickName);
            }

            adUserService.addUser(user);

            publishNewUserService.publish(user);

            return user;
        }
    }
}
