package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.ResourceDM.ChatUser;
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

    ChatUser user;


    @BeforeEach
    public void init(){

        user = new ChatUser();
        user.setName(USER_NAME1);
        user.setNickName(NICKNAME1);
        user.setPassword(PASSWORD1);

        ReflectionTestUtils.setField(allUsers, "allUsers", new ArrayList<>());
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

        ResponseEntity<ChatUser> user1 = restControllerApp.login(user);

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

        restControllerApp.login(user);

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

        user.setNickName(null);
        ResponseEntity<ChatUser> user1 = restControllerApp.login(user);

        verify(publishMessageMock, times(1)).publish(any());

        Assert.assertTrue("should contain "+PREF+USER_NAME1, user1.getBody().getId().contains(PREF+USER_NAME1));
        Assert.assertEquals(USER_NAME1, user1.getBody().getName());
        Assert.assertEquals(USER_NAME1, user1.getBody().getNickName());
        Assert.assertEquals(PASSWORD1, user1.getBody().getPassword());
    }

    @Test
    public void loginExistUser_fail() {

        try {
            restControllerApp.login(user);
            restControllerApp.login(user);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertTrue("expected: "+ USER_NAME_ALREADY_EXIST_MESSAGE + ", get: " + ex.getMessage(),
                    ex.getMessage().contains(USER_NAME_ALREADY_EXIST_MESSAGE));
            Assert.assertTrue("expected: "+ NICK_NAME_ALREADY_EXIST_MESSAGE + ", get: " + ex.getMessage(),
                    ex.getMessage().contains(NICK_NAME_ALREADY_EXIST_MESSAGE));
        }
    }

        @Test
        public void loginUser_shortUserName_fail(){

            user.setName("us");

            try{
                restControllerApp.login(user);
                Assert.fail();
            }catch (BadRequestException ex){
                Assert.assertTrue("not valid userName, ", ex.getMessage().contains(NO_VALID_USERNAME_MESSAGE));
            }
    }

    @Test
    public void loginUser_shortPassword_fail(){

        user.setPassword("12");
        try{
            restControllerApp.login(user);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertTrue(ex.getMessage().contains(NO_VALID_PASSWORD_MESSAGE));
        }
    }

    @Test
    public void loginUser_shortUserNameShortPassword_fail(){

        user.setName("us");
        user.setPassword("12");
        try{
            restControllerApp.login(user);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertTrue(ex.getMessage().contains(NO_VALID_USERNAME_MESSAGE));
            Assert.assertTrue(ex.getMessage().contains(NO_VALID_PASSWORD_MESSAGE));
        }
    }
}
