package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class PublishMessageTest extends BaseTest {

    private static final String ADD_USER = "ADD_USER";
    private AppMessage appMessage;
    private String MESSAGE_CONTEXCT = "new message";
    @Autowired
    private RestControllerApp restControllerApp;


    @BeforeEach
    public void init(){
        appMessage = new AppMessage();
        appMessage.setId(USER_ID1);
        appMessage.setName(USER_NAME1);
        appMessage.setType(ADD_USER_TYPE);
        appMessage.setMsgContext(MESSAGE_CONTEXCT);

        ReflectionTestUtils.setField(restControllerApp, "allUsers", buildAllUsers());
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

        Assert.assertEquals(MESSAGE_TYPE, argumentCaptor.getValue().getType());
        Assert.assertEquals(USER_NAME1, argumentCaptor.getValue().getName());
        Assert.assertEquals(USER_ID1, argumentCaptor.getValue().getId());
        Assert.assertEquals(MESSAGE_CONTEXCT, argumentCaptor.getValue().getMsgContext());

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
