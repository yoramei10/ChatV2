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

}
