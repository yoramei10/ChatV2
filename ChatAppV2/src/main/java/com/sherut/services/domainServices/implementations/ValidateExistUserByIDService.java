package com.sherut.services.domainServices.implementations;

import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.interfaces.IValidateExistUserByIDService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateExistUserByIDService implements IValidateExistUserByIDService {

    @Override
    public boolean validate(List<ChatUser> allUsers, String id) {
        return allUsers
                .stream()
                .anyMatch(user -> id.equalsIgnoreCase(user.getId()));
    }
}
