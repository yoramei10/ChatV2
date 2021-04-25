package com.sherut.apiTests;

import com.sherut.exceptions.BadRequestException;
import com.sherut.models.ResourceDM.ChatUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersTest extends BaseTest{


    @BeforeEach
    public void init(){

        Mockito.when(userRepositoryMock.findAll()).thenReturn(buildAllUsers());

    }

    @After
    public void after(){
        ReflectionTestUtils.setField(restControllerApp, "allUsers", new ArrayList<>());
    }

    @Test
    public void getAllUser_admin_Success(){

        ResponseEntity<List<ChatUser>> allUsers = restControllerApp.getAllUsers(ADMIN);

        Assert.assertEquals(2, allUsers.getBody().size());
        Assert.assertEquals(USER_ID1, allUsers.getBody().get(0).getId());
        Assert.assertEquals(USER_NAME1,allUsers.getBody().get(0).getName());
        Assert.assertEquals(USER_ID1, allUsers.getBody().get(0).getId());
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
