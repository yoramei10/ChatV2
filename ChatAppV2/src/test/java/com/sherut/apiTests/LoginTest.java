package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.services.domainServices.implementations.BuildAppMessageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.junit.Assert;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class LoginTest extends BaseTest {

    @Autowired
    private RestControllerApp restControllerApp;
    @Autowired
    private BuildAppMessageService buildAppMessageService;


    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(restControllerApp, "allUsers", new ArrayList<ChatUser>());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void after(){
        ReflectionTestUtils.setField(buildAppMessageService, "MASK_ID_NAME", true);
    }

    @Test
    public void loginNewUser_Success(){

        ResponseEntity<ChatUser> user1 = restControllerApp.login(USER_NAME1, USER_NAME1, NICKNAME1);

        ArgumentCaptor<AppMessage> argumentCaptor = ArgumentCaptor.forClass(AppMessage.class);

        verify(publishMessageMock, times(1)).publish(argumentCaptor.capture());

        Assert.assertTrue("should contain "+PREF+USER_NAME1, user1.getBody().getId().contains(PREF+USER_NAME1));
        Assert.assertEquals(USER_NAME1, user1.getBody().getName());
        Assert.assertEquals(NICKNAME1, user1.getBody().getNickName());
        Assert.assertEquals(PASSWORD1, user1.getBody().getPassword());

        AppMessage message = argumentCaptor.getValue();
        Assert.assertNull("should be null", message.getId());
        Assert.assertNull("should be null", message.getName());
        Assert.assertEquals(ADD_USER_TYPE, message.getType());
        Assert.assertEquals(NICKNAME1, message.getNickName());
        Assert.assertTrue(message.getMsgContext().toString().contains("add new user"));
    }

    @Test
    public void loginNewUser_PublishNewUserIncludeId_Success(){

        ReflectionTestUtils.setField(buildAppMessageService, "MASK_ID_NAME", false);

        restControllerApp.login(USER_NAME1, USER_NAME1, NICKNAME1);

        ArgumentCaptor<AppMessage> argumentCaptor = ArgumentCaptor.forClass(AppMessage.class);

        verify(publishMessageMock, times(1)).publish(argumentCaptor.capture());

        AppMessage message = argumentCaptor.getValue();
        Assert.assertTrue(message.getId().contains(PREF+USER_NAME1));
        Assert.assertEquals(USER_NAME1, message.getName());
        Assert.assertEquals(ADD_USER_TYPE, message.getType());
        Assert.assertEquals(NICKNAME1, message.getNickName());
        Assert.assertTrue(message.getMsgContext().toString().contains("add new user"));
    }

    @Test
    public void loginNewUser_noNickName_Success(){

        ResponseEntity<ChatUser> user1 = restControllerApp.login(USER_NAME1, USER_NAME1, null);

        verify(publishMessageMock, times(1)).publish(any());

        Assert.assertTrue("should contain "+PREF+USER_NAME1, user1.getBody().getId().contains(PREF+USER_NAME1));
        Assert.assertEquals(USER_NAME1, user1.getBody().getName());
        Assert.assertEquals(USER_NAME1, user1.getBody().getNickName());
        Assert.assertEquals(PASSWORD1, user1.getBody().getPassword());
    }

    @Test
    public void loginExistUser_fail() {

        try {
            restControllerApp.login(USER_NAME1, USER_NAME1, null);
            restControllerApp.login(USER_NAME1, USER_NAME1, null);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertEquals("user already exist", ex.getMessage());
        }
    }

        @Test
        public void loginUser_shortUserName_fail(){

            String USER_NAME = "us";
            String PASSWORD = "user1";

            try{
                restControllerApp.login(USER_NAME, PASSWORD, null);
                Assert.fail();
            }catch (BadRequestException ex){
                Assert.assertEquals("not valid userName, ", ex.getMessage());
            }
    }

    @Test
    public void loginUser_shortPassword_fail(){

        String PASSWORD = "12";
        try{
            restControllerApp.login(USER_NAME1, PASSWORD, null);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertEquals("not valid password", ex.getMessage());
        }
    }

    @Test
    public void loginUser_shortUserNameShortPassword_fail(){

        String USER_NAME = "us";
        String PASSWORD = "12";
        try{
            restControllerApp.login(USER_NAME, PASSWORD, null);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertEquals("not valid userName, not valid password", ex.getMessage());
        }
    }
}
