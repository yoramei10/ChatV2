package com.sherut.apiTests;

import com.sherut.exceptions.EntityNotFoundException;
import com.sherut.models.ResourceModels.AppMessage;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RemoveUserTest extends BaseTest{

    @BeforeEach
    public void init(){

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
        }catch (EntityNotFoundException ex){
            Assert.assertEquals(FAIL_REMOVE_USER_MESSAGE, ex.getMessage());
        }
        verify(publishMessageMock, times(0)).publish(any());
    }

}
