package com.sherut.services.applicationServices.implementations;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DModels.interfaces.IAllUserDM;
import com.sherut.models.DModels.interfaces.IChatUserDM;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.applicationServices.interfaces.IGetAllNickNamesApplicationService;
import com.sherut.services.domainServices.interfaces.IGetAllUsersService;
import com.sherut.services.domainServices.interfaces.IValidateExistUserByIDService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GetAllNickNamesApplicationService implements IGetAllNickNamesApplicationService {

    @Autowired
    private IValidateExistUserByIDService validateExistUserByIDService;
    @Autowired
    private IGetAllUsersService getAllUsersService;

    @Override
    public List<String> getAllNickNames(String id) {

        if(validateExistUserByIDService.validate(getAllUsersService.getAllUsers(), id)) {
            return getAllUsersService.getAllUsers()
                    .stream()
                    .filter(Objects::nonNull)
                    .map(user -> user.getNickName())
                    .collect(Collectors.toList());
        }else{
            throw new BadRequestException("wrong user");
        }
    }
}
