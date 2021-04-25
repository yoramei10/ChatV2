package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.EntityNotFoundException;
import com.sherut.mappers.interfaces.IMapChatUser;
import com.sherut.messaging.interfaces.IPublishMessageGWService;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.repository.interfaces.IMessageRepository;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.applicationServices.interfaces.ILogOutApplicationService;
import com.sherut.services.domainServices.interfaces.IAddMessageTODBService;
import com.sherut.services.domainServices.interfaces.IPublishMessageService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LogoutUserApplicationService implements ILogOutApplicationService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IMapChatUser mapChatUser;

    @Autowired
    private IValidateExistUserService existUserService;

    @Autowired
    @Qualifier("addRemoveMessageToDB")
    private IAddMessageTODBService addRemoveUserMessageTODBService;

    @Autowired
    IPublishMessageService publishMessageService;


    @Override
    public ChatUser logoutUser(String id) {


        IChatUserDTO userDTO = existUserService.validate(id);

        if (null != userDTO){

            userRepository.deleteById(id);

            IAppMessageDTO appMessageDTO = addRemoveUserMessageTODBService.add(userDTO, AppMessageTypeENUM.REMOVE_USER, null);

            publishMessageService.publish(appMessageDTO);

            return mapChatUser.map(userDTO);

        }else{
            throw new EntityNotFoundException("fail remove user. not found");
        }

    }
}
