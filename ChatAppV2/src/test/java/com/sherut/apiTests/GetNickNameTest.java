package com.sherut.apiTests;

import com.sherut.exceptions.BadRequestException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetNickNameTest extends BaseTest{

    @BeforeEach
    public void init(){

        Mockito.when(userRepositoryMock.findAll()).thenReturn(buildAllUsers());
        Mockito.when(userRepositoryMock.getById(USER_ID1)).thenReturn(buildUser());

    }

    @Test
    public void getNickName_Success(){

        ResponseEntity<List<String>> allNickNames = restControllerApp.getAllNickNames(USER_ID1);

        verify(userRepositoryMock, times(1)).getById(USER_ID1);
        verify(userRepositoryMock, times(1)).findAll();

        Assert.assertEquals(NICKNAME1, allNickNames.getBody().get(0));
        Assert.assertEquals(NICKNAME2, allNickNames.getBody().get(1));
    }

    @Test
    public void getNickNameByAdmin_Success(){

        ResponseEntity<List<String>> allNickNames = restControllerApp.getAllNickNames(ADMIN);

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
