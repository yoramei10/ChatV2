package com.sherut.models.DModels.implementations;

import com.sherut.models.DModels.interfaces.IAllUserDM;
import com.sherut.models.ResourceModels.ChatUser;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class AllUserDM implements IAllUserDM {

    @Getter
    List<ChatUser> allUsers;

    @PostConstruct
    public void postConst(){
        allUsers = new ArrayList<>();
    }

    @Override
    public List<ChatUser> getAllUsers() {
        return allUsers;
    }

    @Override
    public void addUser(ChatUser user) {

        allUsers.add(user);

    }

}
