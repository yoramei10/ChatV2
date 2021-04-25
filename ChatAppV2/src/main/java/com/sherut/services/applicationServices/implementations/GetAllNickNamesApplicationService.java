package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.applicationServices.interfaces.IGetAllNickNamesApplicationService;
import com.sherut.services.domainServices.interfaces.IIsUniqueService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllNickNamesApplicationService implements IGetAllNickNamesApplicationService {

    @Autowired
    private IValidateExistUserService validateExistUserByIDService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Qualifier("IsUniqueNickName")
    private IIsUniqueService isUniqueNickService;
    @Autowired
    private IValidateExistUserService validateExistUserService;

    @Override
    public List<String> getAllNickNames(String id) {

        List<IChatUserDTO> allUsersDTO = userRepository.findAll();

        if (null != validateExistUserService.validate(id)) {
            throw new BadRequestException("wrong user");
        }

        return null;
    }
}
