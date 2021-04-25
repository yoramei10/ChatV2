package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.mappers.interfaces.IMapChatUser;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.applicationServices.interfaces.IGetAllUsersApplicationService;
import com.sherut.services.domainServices.interfaces.IValidateAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetAllUsersApplicationService implements IGetAllUsersApplicationService {

    @Autowired
    private IValidateAdminService validateAdminService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IMapChatUser mapChatUser;

    @Override
    public List<ChatUser> getAllUsers(String adminId) {

        if (validateAdminService.validateAdmin(adminId)){

            List<IChatUserDTO> allUsersDTO = userRepository.findAll();

            return Optional
                    .ofNullable(allUsersDTO)
                    .orElse(new ArrayList<>())
                    .stream()
                    .filter(Objects::nonNull)
                    .map(user -> mapChatUser.map(user))
                    .collect(Collectors.toList());

        }else{
            throw new BadRequestException("no permission exception");
        }
    }
}
