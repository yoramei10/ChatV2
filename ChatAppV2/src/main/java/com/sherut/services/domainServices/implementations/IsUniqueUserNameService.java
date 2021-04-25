package com.sherut.services.domainServices.implementations;


import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.services.domainServices.interfaces.IIsUniqueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IsUniqueUserName")
public class IsUniqueUserNameService implements IIsUniqueService {

    public boolean isUnique(List<IChatUserDTO> allUsers, String userName) {

        if (null == allUsers || allUsers.isEmpty())
            return true;

        return !allUsers
                .stream()
                .anyMatch(user -> userName.equalsIgnoreCase(user.getUserName()));
    }
}
