package com.sherut.models.DModels.interfaces;

public interface IAppMessageDM {

    String getId();
    void setId(String id);

    String getName();
    void setName(String name);

    Object getMsgContext();
    void setMsgContext(Object msgContext);
}
