package com.sherut.services.domainServices.implementations;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.interfaces.IAddMessageTODBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("addNewUserMessageToDB")
public class AddNewUserMessageTODBService implements IAddMessageTODBService {


    @Value("${appMessage.messageContext.addNewUser}")
    String newUserMessageContext ;

    @Autowired
    @Qualifier("addMessageToDB")
    private IAddMessageTODBService addMessageTODBService;


    @Override
    public IAppMessageDTO add(IChatUserDTO userDTO, AppMessageTypeENUM type, Object messageContext) {

        return addMessageTODBService.add(userDTO, AppMessageTypeENUM.ADD_USER, newUserMessageContext + userDTO.getNickName());

    }
}
