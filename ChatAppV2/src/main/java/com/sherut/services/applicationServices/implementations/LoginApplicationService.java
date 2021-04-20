package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DModels.interfaces.IValidateDM;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.applicationServices.interfaces.ILoginApplicationService;
import com.sherut.config.ConfigurationVariablesApp;
import com.sherut.services.domainServices.interfaces.IPublishUserService;
import com.sherut.services.domainServices.interfaces.IValidateNewUserService;
import com.sherut.services.domainServices.interfaces.IValidateUserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginApplicationService implements ILoginApplicationService {

    @Autowired
    private IValidateNewUserService validateNewUserService;
    @Autowired
    private IValidateUserInputService validateUserInputService;
    @Autowired
    @Qualifier("publishNewUserService")
    private IPublishUserService publishNewUserService;


    AtomicInteger id = new AtomicInteger();
    String USER_PREF = ConfigurationVariablesApp.userPref;

    public ChatUser loginApp (List<ChatUser> allUsers, String userName, String password, String nickName){

        IValidateDM validateDM = validateUserInputService.validate(userName, password);
        if (!validateDM.getValue()){
            throw new BadRequestException(validateDM.getValidateMessage());
        }

        if (validateNewUserService.checkNewUser(allUsers, userName)){

            ChatUser user = new ChatUser();
            user.setName(userName);
            user.setId(USER_PREF + userName + "_" + id.incrementAndGet());
            user.setPassword(password);

            if (null == nickName || nickName.length() == 0){
                user.setNickName(userName);
            }else{
                user.setNickName(nickName);
            }

            allUsers.add(user);

            publishNewUserService.publish(user);

            return user;
        }else {
            throw new BadRequestException("user already exist");
        }
    }
}
