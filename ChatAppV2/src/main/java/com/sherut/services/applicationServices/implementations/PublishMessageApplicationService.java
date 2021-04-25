package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.repository.interfaces.IMessageRepository;
import com.sherut.services.applicationServices.interfaces.IPublishMessageApplicationService;
import com.sherut.messaging.interfaces.IPublishMessageGWService;
import com.sherut.services.domainServices.interfaces.IAddMessageTODBService;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PublishMessageApplicationService implements IPublishMessageApplicationService {

    @Autowired
    private IPublishMessageGWService publishMessage;
    @Autowired
    private IValidateExistUserService validateExistUserService;
    @Autowired
    private IMessageRepository chatRepository;
    @Autowired
    private IMapAppMessage mapAppMessage;
    @Autowired
    private IBuildAppMessageService buildAppMessageService;
    @Autowired
    @Qualifier("addMessageToDB")
    private IAddMessageTODBService addMessageTODBService;



    @Override
    public void publish(String userId, Object messageContext) {

        IChatUserDTO userDTO = validateExistUserService.validate(userId);

        if (null != userDTO) {
            IAppMessageDTO messageDTO = addMessageTODBService.add(userDTO, AppMessageTypeENUM.MESSAGE, messageContext);

            if (null != messageDTO) {
                AppMessage messageToPublish = mapAppMessage.map(messageDTO);
                publishMessage.publish(messageToPublish);
            }
        }else{
            throw new BadRequestException("wrong user");
        }

    }
}
