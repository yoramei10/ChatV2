package com.sherut.mappers.implementation;

import com.sherut.mappers.interfaces.IMapChatUser;
import com.sherut.models.DTO.implementations.ChatUserDTO;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.DTO.interfaces.IFactoryDM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MapChatUser implements IMapChatUser {

    @Autowired
    private IFactoryDM factoryDM;

    @Override
    public IChatUserDTO map(ChatUser chatUser) {
        if (null == chatUser)
            return null;

        IChatUserDTO chatUserDM = new ChatUserDTO(chatUser.getName());
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
        chatUser.setName(chatUserDM.getUserName());
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
