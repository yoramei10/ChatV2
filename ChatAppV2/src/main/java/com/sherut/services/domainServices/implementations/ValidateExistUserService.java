package com.sherut.services.domainServices.implementations;

import com.sherut.exceptions.EntityNotFoundException;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.domainServices.interfaces.IValidateExistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ValidateExistUserService implements IValidateExistUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public IChatUserDTO validate(String id) {

        return userRepository.getById(id);
    }
}
