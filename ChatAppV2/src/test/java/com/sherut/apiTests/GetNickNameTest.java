package com.sherut.apiTests;

import com.sherut.exceptions.BadRequestException;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

public class GetNickNameTest extends BaseTest{

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
    public void after(){
        ReflectionTestUtils.setField(allUsers, "allUsers", new ArrayList<>());
    }

    @Test
    public void getCorrectNickName_Success(){

        ResponseEntity<List<String>> allNickNames = restControllerApp.getAllNickNames(USER_ID1);

        Assert.assertEquals(NICKNAME1, allNickNames.getBody().get(0));
        Assert.assertEquals(NICKNAME2, allNickNames.getBody().get(1));
    }

    @Test
    public void getNickName_wrongUser_fail(){

        try{
            restControllerApp.getAllNickNames("not exist user");
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertEquals("wrong user", ex.getMessage());
        }
    }

}
