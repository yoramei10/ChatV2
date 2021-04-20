package com.sherut.models.DModels.impl;

import com.sherut.models.DModels.interfaces.IAppMessageDM;

public class AppMessageDM implements IAppMessageDM {

    String id;
    String name;
    Object msgContext;

    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getMsgContext() {
        return msgContext;
    }
    @Override
    public void setMsgContext(Object msgContext) {
        this.msgContext = msgContext;
    }
}
