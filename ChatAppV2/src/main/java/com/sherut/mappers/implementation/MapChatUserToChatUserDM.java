package com.sherut.mappers.implementation;

import com.sherut.mappers.interfaces.IMapChatUserToChatUserDM;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.DModels.interfaces.IChatUserDM;
import com.sherut.config.IFactoryDM;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapChatUserToChatUserDM implements IMapChatUserToChatUserDM {

    @Autowired
    private IFactoryDM factoryDM;

    @Override
    public IChatUserDM map(ChatUser chatUser) {
        if (null == chatUser)
            return null;

        IChatUserDM chatUserDM = factoryDM.getChatUserDM();
        chatUserDM.setId(chatUser.getId());
        chatUserDM.setName(chatUser.getName());
        chatUserDM.setPassword(chatUser.getPassword());
        chatUserDM.setNickName(chatUser.getNickName());
        return chatUserDM;
    }

    @Override
    public ChatUser map(IChatUserDM chatUserDM) {

        if (null == chatUserDM)
            return null;

        ChatUser chatUser = new ChatUser();
        chatUser.setId(chatUserDM.getId());
        chatUser.setName(chatUserDM.getName());
        chatUser.setPassword(chatUserDM.getPassword());
        chatUser.setNickName(chatUserDM.getNickName());
        return chatUser;
    }

    @Override
    public List<IChatUserDM> mapList(List<ChatUser> chatUsers) {

        return chatUsers.stream()
                .filter(Objects::nonNull)
                .map(usr -> map(usr))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatUser> map(List<IChatUserDM> chatUserDMs) {

        return chatUserDMs.stream()
                .filter(Objects::nonNull)
                .map(usr -> map(usr))
                .collect(Collectors.toList());
    }
}
