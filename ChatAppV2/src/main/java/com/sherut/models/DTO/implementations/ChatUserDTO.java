package com.sherut.models.DTO.implementations;

import com.sherut.models.DTO.interfaces.IChatUserDTO;
import lombok.Getter;
import lombok.Setter;

public class ChatUserDTO implements IChatUserDTO {

    @Getter @Setter
    String id ;
    @Getter @Setter
    String name;
    @Getter @Setter
    String nickName;
    @Getter @Setter
    String password;

}
