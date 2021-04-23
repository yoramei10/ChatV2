package com.sherut.models.DTO.implementations;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import lombok.Getter;
import lombok.Setter;

public class AppMessageDTO implements IAppMessageDTO {

    @Getter @Setter
    String id;
    @Getter @Setter
    String name;
    @Getter @Setter
    Object msgContext;

}
