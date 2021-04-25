package com.sherut.models.DTO.interfaces;

import com.sherut.models.enums.AppMessageTypeENUM;

public interface IAppMessageDTO {

    String getId();
    void setId(String id);

    String getUserName();
    void setUserName(String userName);

    String getUserId();
    void setUserId(String UserId);

    String getNickName();
    void setNickName(String nickName);

    AppMessageTypeENUM getType();
    void setType(AppMessageTypeENUM nickName);


    Object getMsgContext();
    void setMsgContext(Object msgContext);
}
