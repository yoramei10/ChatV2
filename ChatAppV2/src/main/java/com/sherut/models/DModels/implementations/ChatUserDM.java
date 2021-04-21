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


//    @Override
//    public String getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String getNickName() {
//        return nickName;
//    }
//
//    @Override
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
