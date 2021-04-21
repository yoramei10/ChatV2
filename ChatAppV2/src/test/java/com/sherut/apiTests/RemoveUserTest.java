package com.sherut.apiTests;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.AppMessage;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RemoveUserTest extends BaseTest{

    @BeforeEach
    public void init(){

        ReflectionTestUtils.setField(restControllerApp, "allUsers", buildAllUsers());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeUser_Success(){

        ArgumentCaptor<AppMessage> argumentCaptor = ArgumentCaptor.forClass(AppMessage.class);

        ResponseEntity<String> response = restControllerApp.removeUser(USER_ID1);

        verify(publishMessageMock, times(1)).publish(argumentCaptor.capture());

        Assert.assertEquals(String.format("user %s was removed", USER_NAME1), response.getBody());
        Assert.assertEquals(REMOVE_USER_TYPE, argumentCaptor.getValue().getType());
    }

    @Test
    public void removeUser_wrongUser_fail(){

        try {
            restControllerApp.removeUser("wrong user");
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertEquals("fail remove user", ex.getMessage());
        }
        verify(publishMessageMock, times(0)).publish(any());
    }

}
