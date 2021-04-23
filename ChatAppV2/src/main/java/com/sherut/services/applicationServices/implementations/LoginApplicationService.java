package com.sherut.services.applicationServices.implementations;

import com.sherut.models.DTO.interfaces.IFactoryDTO;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.services.applicationServices.interfaces.ILoginApplicationService;
import com.sherut.config.ConfigurationVariablesApp;
import com.sherut.services.domainServices.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    private IFactoryDTO factoryDM;
    @Autowired
    private IValidateUniqueUserService validateUniqueUserService;


    AtomicInteger id = new AtomicInteger();
    String USER_PREF = ConfigurationVariablesApp.userPref;

    public ChatUser loginApp (ChatUser user) {

        IValidateDM validateDM = factoryDM.getValidateDTO();
        validateDM.setValue(true);
        validateDM.setValidateMessage("");

        validateDM = validateUserInputService.validate(user, validateDM);
        if (!validateDM.getValue()) {
            throw new BadRequestException(validateDM.getValidateMessage());
        }

        validateDM = validateUniqueUserService.validate(user, getAllUsersService.getAllUsers(), validateDM);
        if (!validateDM.getValue()) {
            throw new BadRequestException(validateDM.getValidateMessage());
        } else {

            if (StringUtils.hasText(user.getNickName())) {
                user.setNickName(user.getNickName());
            } else {
                user.setNickName(user.getName());
            }

            user.setId(USER_PREF + user.getName() + "_" + id.incrementAndGet());
            adUserService.addUser(user);

            publishNewUserService.publish(user);

            return user;
        }
    }
}
