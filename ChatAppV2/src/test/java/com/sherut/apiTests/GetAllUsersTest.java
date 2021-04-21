package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceModels.ChatUser;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

public class GetAllUsersTest extends BaseTest{


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
    public void getAllUser_admin_Success(){

        ResponseEntity<List<ChatUser>> allUsers = restControllerApp.getAllUsers(ADMIN);

        Assert.assertTrue(allUsers.getBody().get(0).getId().contains(PREF+USER_NAME1));
        Assert.assertEquals(USER_NAME1,allUsers.getBody().get(0).getName());
        Assert.assertTrue(allUsers.getBody().get(1).getId().contains(PREF+USER_NAME2));
        Assert.assertEquals(USER_NAME2,allUsers.getBody().get(1).getName());
    }

    @Test
    public void getNickName_wrongUser_fail(){

        try{
            restControllerApp.getAllUsers(USER_NAME1);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertEquals("no permission exception", ex.getMessage());
        }
    }

}
