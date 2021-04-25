package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.repository.interfaces.IMessageRepository;
import com.sherut.services.applicationServices.interfaces.IGetAllMessagesApplicationService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetAllMessagesApplicationService implements IGetAllMessagesApplicationService {

    @Autowired
    private IValidateExistUserService validateExistUserService;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IMapAppMessage mapAppMessage;


    @Override
    public List<AppMessage> getALlMessages(String id) {

        IChatUserDTO userDTO = validateExistUserService.validate(id);

        if (null != userDTO) {
            List<IAppMessageDTO> allMessagesTDO = messageRepository.findAll();

            return Optional.ofNullable(allMessagesTDO)
                    .orElse(new ArrayList<>())
                    .stream()
                    .filter(Objects::nonNull)
                    .map(messageDTO -> mapAppMessage.map(messageDTO))
                    .collect(Collectors.toList());
        } else {
            throw new BadRequestException("wrong user");
        }
    }
}
