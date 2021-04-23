package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceDM.AppMessage;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class PublishMessageTest extends BaseTest {

    private static final String ADD_USER = "ADD_USER";
    private AppMessage appMessage;
    private static final String MESSAGE_CONTEXCT = "new message";

    @Autowired
    private RestControllerApp restControllerApp;


    @BeforeEach
    public void init(){
        appMessage = buildMessage(USER_ID1, USER_NAME1, ADD_USER_TYPE, MESSAGE_CONTEXCT);

        ReflectionTestUtils.setField(allUsers, "allUsers", buildAllUsers());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterTests(){
        ReflectionTestUtils.setField(allUsers, "allUsers", new ArrayList<>());
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
        Assert.assertNull(null, message.getName());
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
