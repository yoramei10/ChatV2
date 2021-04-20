package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DModels.interfaces.IChatUserDM;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.applicationServices.interfaces.IGetAllNickNamesApplicationService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GetAllNickNamesApplicationService implements IGetAllNickNamesApplicationService {

    @Override
    public List<String> getAllNickNames(String id, List<ChatUser> allUsers) {

        if(allUsers
                .stream()
                .filter(Objects::nonNull)
                .anyMatch(user -> id.equalsIgnoreCase(user.getId()))) {
            return allUsers
                    .stream()
                    .filter(Objects::nonNull)
                    .map(user -> user.getNickName())
                    .collect(Collectors.toList());
        }else{
            throw new BadRequestException("wrong user");
        }
    }
}
