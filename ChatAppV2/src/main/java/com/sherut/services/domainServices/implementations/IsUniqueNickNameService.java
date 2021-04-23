package com.sherut.services.domainServices.implementations;


import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.services.domainServices.interfaces.IIsUniqueService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("IsUniqueNickName")
public class IsUniqueNickNameService implements IIsUniqueService {

    public boolean isUnique(List<ChatUser> allUsers, String nickName) {

        if(StringUtils.hasText(nickName)) {
            return !allUsers
                    .stream()
                    .anyMatch(user -> nickName.equalsIgnoreCase(user.getNickName()));
        }
        return true;
    }
}
