package com.sherut.models.ResourceModels;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AppMessage implements Serializable {

    @Getter
    @Setter
    String id;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String nickName;

    @Getter
    @Setter
    String type;

    @Getter
    @Setter
    Object msgContext;
}
