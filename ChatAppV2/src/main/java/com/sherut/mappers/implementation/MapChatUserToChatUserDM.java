package com.sherut.mappers.implementation;

import com.sherut.mappers.interfaces.IMapChatUserToChatUserDM;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.DTO.interfaces.IFactoryDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapChatUserToChatUserDM implements IMapChatUserToChatUserDM {

    @Autowired
    private IFactoryDTO factoryDM;

    @Override
    public IChatUserDTO map(ChatUser chatUser) {
        if (null == chatUser)
            return null;

        IChatUserDTO chatUserDM = factoryDM.getChatUserDTO();
        chatUserDM.setId(chatUser.getId());
        chatUserDM.setName(chatUser.getName());
        chatUserDM.setPassword(chatUser.getPassword());
        chatUserDM.setNickName(chatUser.getNickName());
        return chatUserDM;
    }

    @Override
    public ChatUser map(IChatUserDTO chatUserDM) {

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
    public List<IChatUserDTO> mapList(List<ChatUser> chatUsers) {

        return chatUsers.stream()
                .filter(Objects::nonNull)
                .map(usr -> map(usr))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatUser> map(List<IChatUserDTO> chatUserDMs) {

        return chatUserDMs.stream()
                .filter(Objects::nonNull)
                .map(usr -> map(usr))
                .collect(Collectors.toList());
    }
}
