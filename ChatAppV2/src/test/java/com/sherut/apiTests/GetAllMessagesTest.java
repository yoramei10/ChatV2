package com.sherut.apiTests;

import com.sherut.messaging.interfaces.IListenerMessageService;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.enums.AppMessageTypeENUM;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class GetAllMessagesTest extends BaseTest {

    @Autowired
    private IListenerMessageService listenerMessageService;

    private AppMessage appMessage;
    private AppMessage appMessage2;

    private IAppMessageDTO appMessageDTO;
    private IAppMessageDTO appMessageDTO2;
    private static final String MESSAGE_CONTEXCT = "new message";


    @BeforeEach
    public void init(){

        appMessageDTO = buildMessage(MESSAGE_ID_1, USER_ID1, USER_NAME1, NICKNAME1, AppMessageTypeENUM.ADD_USER, MESSAGE_CONTEXCT);
        appMessageDTO2 = buildMessage(MESSAGE_ID_1, USER_ID2, USER_NAME2, NICKNAME2, AppMessageTypeENUM.ADD_USER, MESSAGE_CONTEXCT);

        List<IAppMessageDTO> allMessageDTO = new ArrayList<>();
        allMessageDTO.add(appMessageDTO);
        allMessageDTO.add(appMessageDTO2);

        Mockito.when(messageRepositoryMock.findAll()).thenReturn(allMessageDTO);
        Mockito.when(userRepositoryMock.getById(any())).thenReturn(buildUser());

    }

    @Test
    public void getAllMessages_Success(){

        ArgumentCaptor<AppMessage> argumentCaptor = ArgumentCaptor.forClass(AppMessage.class);

        listenerMessageService.consume(appMessage);
        listenerMessageService.consume(appMessage2);

        ResponseEntity<List<AppMessage>> allMessages = restControllerApp.getAllMessages(USER_ID1);

        Assert.assertEquals(2, allMessages.getBody().size());
        Assert.assertEquals(appMessageDTO.getNickName(), allMessages.getBody().get(0).getNickName());
        Assert.assertEquals(appMessageDTO2.getNickName(), allMessages.getBody().get(1).getNickName());

    }
}
