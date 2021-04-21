package com.sherut.apiTests;

import com.sherut.messaging.interfaces.IListenerMessageService;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

public class GetAllMessagesTest extends BaseTest {

    @Autowired
    private IListenerMessageService listenerMessageService;

    private AppMessage appMessage;
    private AppMessage appMessage2;
    private static final String MESSAGE_CONTEXCT = "new message";


    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(allUsers, "allUsers", buildAllUsers());

        appMessage = buildMessage(USER_ID1, USER_NAME1, ADD_USER_TYPE, MESSAGE_CONTEXCT);
        appMessage2 = buildMessage(USER_ID2, USER_NAME2, ADD_USER_TYPE, MESSAGE_CONTEXCT);

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after(){
        ReflectionTestUtils.setField(allUsers, "allUsers", new ArrayList<ChatUser>());
    }

    @Test
    public void getAllMessages_Success(){

        ArgumentCaptor<AppMessage> argumentCaptor = ArgumentCaptor.forClass(AppMessage.class);

        listenerMessageService.consume(appMessage);
        listenerMessageService.consume(appMessage2);

        ResponseEntity<List<AppMessage>> allMessages = restControllerApp.getAllMessages(USER_ID1);

        Assert.assertEquals(2, allMessages.getBody().size());

    }
}
