package com.sherut.models.DTO.interfaces;

public interface IAppMessageDTO {

    String getId();
    void setId(String id);

    String getName();
    void setName(String name);

    Object getMsgContext();
    void setMsgContext(Object msgContext);
}
