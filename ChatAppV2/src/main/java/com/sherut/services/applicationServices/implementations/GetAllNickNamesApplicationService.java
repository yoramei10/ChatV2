package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.mappers.interfaces.IMapChatUser;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.applicationServices.interfaces.IGetAllNickNamesApplicationService;
import com.sherut.services.domainServices.interfaces.IValidateAdminService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetAllNickNamesApplicationService implements IGetAllNickNamesApplicationService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IValidateExistUserService validateExistUserService;
    @Autowired
    private IMapChatUser mapChatUser;
    @Autowired
    private IValidateAdminService validateAdminService;

    @Override
    public List<String> getAllNickNames(String id) {

        if (validateAdminService.validateAdmin(id) ||
                (null != validateExistUserService.validate(id))) {

            List<IChatUserDTO> allUsersDTO = userRepository.findAll();

            return Optional
                    .ofNullable(allUsersDTO)
                    .orElse(new ArrayList<>())
                    .stream()
                    .filter(Objects::nonNull)
                    .map(user -> user.getNickName())
                    .collect(Collectors.toList());
        } else {
            throw new BadRequestException("wrong user");
        }
    }
}
