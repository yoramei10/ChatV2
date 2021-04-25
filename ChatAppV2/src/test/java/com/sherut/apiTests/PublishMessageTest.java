package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DTO.implementations.ChatUserDTO;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.repository.interfaces.IMessageRepository;
import com.sherut.repository.interfaces.IUserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class PublishMessageTest extends BaseTest {

    private static final String ADD_USER = "ADD_USER";
    private AppMessage appMessage;
    private IAppMessageDTO appMessageDTO;
    private static final String MESSAGE_CONTEXCT = "new message";

    @Autowired
    private RestControllerApp restControllerApp;

//    @MockBean
//    IMessageRepository messageRepositoryMock1;
//    @MockBean
//    IUserRepository userRepositoryMock1;

    IChatUserDTO userDTO;

    @BeforeEach
    public void init(){

        userDTO = new ChatUserDTO(USER_NAME1);
        userDTO.setId(USER_ID1);
        userDTO.setNickName(NICKNAME1);
        userDTO.setPassword(PASSWORD1);

        appMessageDTO = buildMessage(MESSAGE_ID_1, USER_ID1, USER_NAME1, NICKNAME1, AppMessageTypeENUM.ADD_USER, MESSAGE_CONTEXCT);

        Mockito.when(userRepositoryMock.getByUserName(any())).thenReturn(userDTO);
        Mockito.when(messageRepositoryMock.insert((IAppMessageDTO) any())).thenReturn(null);

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void publishUserMessage_Success(){

        ArgumentCaptor<AppMessage> argumentCaptor = ArgumentCaptor.forClass(AppMessage.class);

        restControllerApp.publishNewMessage(USER_ID1, appMessage);

        verify(publishMessageMock, times(1)).publish(argumentCaptor.capture());

        AppMessage message = argumentCaptor.getValue();
        Assert.assertEquals(MESSAGE_TYPE, message.getType());
        Assert.assertEquals(USER_NAME1, message.getNickName());
        Assert.assertNull(null, message.getId());
        Assert.assertNull(null, message.getUserName());
        Assert.assertEquals(MESSAGE_CONTEXCT, message.getMsgContext());

    }

    @Test
    public void publishUserMessage_wrongUser_fail(){

        try {
            restControllerApp.publishNewMessage("wrongID", appMessage);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertEquals("wrong user", ex.getMessage());
        }
        verify(publishMessageMock, times(0)).publish(any());
    }

}
