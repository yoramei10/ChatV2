package com.sherut.models.DTO.implementations;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.enums.AppMessageTypeENUM;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class AppMessageDTO implements IAppMessageDTO {

    @Id
    @Indexed(useGeneratedName = true)
    @Getter
    @Setter
    String id;

    @Getter
    @Setter
    AppMessageTypeENUM type;

    @Getter
    @Setter
    Object msgContext;

    @Getter
    @Setter
    String userId;

    @Getter
    @Setter
    String userName;

    @Getter
    @Setter
    String nickName;

}
