package com.sherut.services.applicationServices.implementations;

import com.sherut.mappers.interfaces.IMapChatUser;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.DTO.interfaces.IFactoryDM;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DM.interfaces.IValidateDM;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.repository.interfaces.IMessageRepository;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.applicationServices.interfaces.ILoginApplicationService;
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
    private IPublishMessageService publishMessageService;
    @Autowired
    private IFactoryDM factoryDM;
    @Autowired
    private IValidateUniqueUserService validateUniqueUserService;
    @Autowired
    private IUserRepository userRepositoryService;
    @Autowired
    private IMapChatUser mapChatUser;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    @Qualifier("addNewUserMessageToDB")
    private IAddMessageTODBService addNewUserMessageTODBService;




    AtomicInteger id = new AtomicInteger();

    public ChatUser loginApp (ChatUser user) {

        IChatUserDTO userDTO = mapChatUser.map(user);

        IValidateDM validateDM = factoryDM.getValidateDM();
        validateDM.setValue(true);
        validateDM.setValidateMessage("");

        validateDM = validateUserInputService.validate(userDTO, validateDM);
        if (!validateDM.getValue()) {
            throw new BadRequestException(validateDM.getValidateMessage());
        }

        validateDM = validateUniqueUserService.validate(userDTO, validateDM);
        if (!validateDM.getValue()) {
            throw new BadRequestException(validateDM.getValidateMessage());
        } else {

            if (StringUtils.hasText(userDTO.getNickName())) {
                userDTO.setNickName(userDTO.getNickName());
            } else {
                userDTO.setNickName(userDTO.getUserName());
            }

            IChatUserDTO createdUserDTO = userRepositoryService.insert(userDTO);

            IAppMessageDTO appAddUserMessageDTO = addNewUserMessageTODBService.add(createdUserDTO, null, null);

            ChatUser createdUser = mapChatUser.map(createdUserDTO);

            publishMessageService.publish(appAddUserMessageDTO);

            return createdUser;
        }
    }
}
