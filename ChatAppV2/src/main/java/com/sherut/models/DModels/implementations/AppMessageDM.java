package com.sherut.models.DModels.implementations;

import com.sherut.models.DModels.interfaces.IAppMessageDM;
import lombok.Getter;
import lombok.Setter;

public class AppMessageDM implements IAppMessageDM {

    @Getter @Setter
    String id;
    @Getter @Setter
    String name;
    @Getter @Setter
    Object msgContext;

//    @Override
//    public String getId() {
//        return id;
//    }
//    @Override
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//    @Override
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public Object getMsgContext() {
//        return msgContext;
//    }
//    @Override
//    public void setMsgContext(Object msgContext) {
//        this.msgContext = msgContext;
//    }
}
