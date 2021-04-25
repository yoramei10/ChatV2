package com.sherut.models.DTO.implementations;

import com.sherut.models.DTO.interfaces.IChatUserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ChatUserDTO implements IChatUserDTO {

    @Id
    @Indexed(useGeneratedName = true)
    @Getter
    @Setter
    String id ;

    @Indexed(unique = true)
    @NonNull
    @Getter
    @Setter
    String userName;

    @Getter
    @Setter
    @Indexed(unique = true)
    String nickName;

    @Getter
    @Setter
    String password;

}
