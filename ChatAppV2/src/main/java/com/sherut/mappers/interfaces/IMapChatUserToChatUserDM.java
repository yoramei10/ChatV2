package com.sherut.mappers.interfaces;

import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.DTO.interfaces.IChatUserDTO;

import java.util.List;

public interface IMapChatUserToChatUserDM {

    IChatUserDTO map(ChatUser chatUser);
    ChatUser map(IChatUserDTO chatUserDM);

    List<IChatUserDTO> mapList(List<ChatUser> chatUsers);
    List<ChatUser> map(List<IChatUserDTO> chatUserDMs);
}
