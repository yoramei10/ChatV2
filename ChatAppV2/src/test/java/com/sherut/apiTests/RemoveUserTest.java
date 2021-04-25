package com.sherut.apiTests;

import com.sherut.exceptions.EntityNotFoundException;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.enums.AppMessageTypeENUM;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RemoveUserTest extends BaseTest{

    @BeforeEach
    public void init(){

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Ignore
    @Test
    public void removeUser_Success(){

        IAppMessageDTO appMessageDTO = buildMessage(MESSAGE_ID_1, USER_ID1, USER_NAME1, NICKNAME1, AppMessageTypeENUM.REMOVE_USER, "remove user ");

        Mockito.when(userRepositoryMock.getById(any())).thenReturn(buildUser());
        Mockito.when(messageRepositoryMock.insert((IAppMessageDTO) any())).thenReturn(appMessageDTO);

        ArgumentCaptor<AppMessage> argumentCaptor = ArgumentCaptor.forClass(AppMessage.class);

        ResponseEntity<String> response = restControllerApp.logOut(USER_ID1);

        verify(publishMessageMock, times(1)).publish(argumentCaptor.capture());

        Assert.assertEquals(String.format("user %s was removed", USER_NAME1), response.getBody());
        Assert.assertEquals(REMOVE_USER_TYPE, argumentCaptor.getValue().getType().name());
    }

    @Test
    public void removeUser_wrongUser_fail(){

        try {
            restControllerApp.logOut("wrong user");
            Assert.fail();
        }catch (EntityNotFoundException ex){
            Assert.assertEquals(FAIL_REMOVE_USER_MESSAGE, ex.getMessage());
        }
        verify(publishMessageMock, times(0)).publish(any());
    }

}
