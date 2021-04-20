package com.sherut.mappers.interfaces;

import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.DModels.interfaces.IChatUserDM;

import java.util.List;

public interface IMapChatUserToChatUserDM {

    IChatUserDM map(ChatUser chatUser);
    ChatUser map(IChatUserDM chatUserDM);

    List<IChatUserDM> mapList(List<ChatUser> chatUsers);
    List<ChatUser> map(List<IChatUserDM> chatUserDMs);
}
