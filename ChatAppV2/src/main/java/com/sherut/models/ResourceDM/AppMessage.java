package com.sherut.models.ResourceDM;


import com.sherut.models.enums.AppMessageTypeENUM;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AppMessage implements Serializable {

    @Getter
    @Setter
    String userId;

    @Getter
    @Setter
    String userName;

    @Getter
    @Setter
    String nickName;

    @Getter
    @Setter
    String id;

    @Getter
    @Setter
    AppMessageTypeENUM type;

    @Getter
    @Setter
    Object msgContext;
}
