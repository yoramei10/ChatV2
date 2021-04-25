package com.sherut.services.domainServices.implementations;

import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.messaging.interfaces.IPublishMessageGWService;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.repository.interfaces.IMessageRepository;
import com.sherut.services.domainServices.interfaces.IAddMessageTODBService;
import com.sherut.services.domainServices.interfaces.IBuildAppMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("addMessageToDB")
public class AddMessageTODBService implements IAddMessageTODBService {

    @Autowired
    private IBuildAppMessageService buildAppMessageService;
    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private IPublishMessageGWService publishMessage;

    @Value("${appMessage.messageContext.addNewUser}")
    String messageContext;

    @Autowired
    private IMapAppMessage mapAppMessage;


    @Override
    public IAppMessageDTO add(IChatUserDTO userDTO, AppMessageTypeENUM type, Object messageContext) {

        if (null != userDTO) {
            IAppMessageDTO appMessageDTO = buildAppMessageService.build(userDTO, type, messageContext);

            return messageRepository.insert(appMessageDTO);

        } else {
            return null;
        }
    }
}
