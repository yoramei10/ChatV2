package com.sherut.models.DModels.implementations;

import com.sherut.models.DModels.interfaces.IChatUserDM;
import lombok.Getter;
import lombok.Setter;

public class ChatUserDM implements IChatUserDM {

    @Getter @Setter
    String id ;
    @Getter @Setter
    String name;
    @Getter @Setter
    String nickName;
    @Getter @Setter
    String password;

}
