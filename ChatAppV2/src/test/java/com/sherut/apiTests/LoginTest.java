package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.exceptions.BadRequestException;
import com.sherut.models.DTO.implementations.ChatUserDTO;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.services.domainServices.implementations.BuildAppMessageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.junit.Assert;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class LoginTest extends BaseTest {

    @Autowired
    private RestControllerApp restControllerApp;
    @Autowired
    private BuildAppMessageService buildAppMessageService;

    ChatUser user;
    IChatUserDTO userDTO;
    IAppMessageDTO messageTDO;

    ArgumentCaptor<AppMessage> messageCaptor = ArgumentCaptor.forClass(AppMessage.class);
    ArgumentCaptor<IChatUserDTO> userRepoCaptor = ArgumentCaptor.forClass(IChatUserDTO.class);
    ArgumentCaptor<IAppMessageDTO> messageRepoCaptor = ArgumentCaptor.forClass(IAppMessageDTO.class);

    @BeforeEach
    public void init(){

        user = new ChatUser();
        user.setName(USER_NAME1);
        user.setNickName(NICKNAME1);
        user.setPassword(PASSWORD1);
        List<ChatUser> objects = java.util.Arrays.asList(user);

        userDTO = new ChatUserDTO(USER_NAME1);
        userDTO.setId(USER_ID1);
        userDTO.setNickName(NICKNAME1);
        userDTO.setPassword(PASSWORD1);
        List<IChatUserDTO> userDTOList = java.util.Arrays.asList(userDTO);

        messageTDO = buildMessage(MESSAGE_ID_1, null, null, NICKNAME1,  AppMessageTypeENUM.ADD_USER, MESSAGE_CONTEXT_NEW_USER);

        when(userRepositoryMock.findAll()).thenReturn(userDTOList);
        when(userRepositoryMock.insert((IChatUserDTO) any())).thenReturn(userDTO);
        when(userRepositoryMock.getById(any())).thenReturn(null);

        when(messageRepositoryMock.insert((IAppMessageDTO) any())).thenReturn(messageTDO);

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

        verify(userRepositoryMock, times(1)).insert(userRepoCaptor.capture());
        verify(messageRepositoryMock, times(1)).insert(messageRepoCaptor.capture());
        verify(publishMessageMock, times(1)).publish(messageCaptor.capture());

        ChatUser user1Body = user1.getBody();
        Assert.assertEquals(USER_ID1, user1Body.getId());
        Assert.assertEquals(USER_NAME1, user1Body.getName());
        Assert.assertEquals(NICKNAME1, user1Body.getNickName());
        Assert.assertEquals(PASSWORD1, user1Body.getPassword());

        IChatUserDTO userCreatedTDO = userRepoCaptor.getValue();
        Assert.assertNull("user id should be null", userCreatedTDO.getId());
        Assert.assertEquals(USER_NAME1, userCreatedTDO.getUserName());
        Assert.assertEquals(PASSWORD1, userCreatedTDO.getPassword());
        Assert.assertEquals(NICKNAME1, userCreatedTDO.getNickName());

        IAppMessageDTO messageCreatedValue = messageRepoCaptor.getValue();

        Assert.assertNull("should be null", messageCreatedValue.getUserId());
        Assert.assertNull("should be null", messageCreatedValue.getUserName());
        Assert.assertEquals(NICKNAME1, messageCreatedValue.getNickName());

        Assert.assertNull("message id should be null", messageCreatedValue.getId());
        Assert.assertEquals(NEW_USER_TYPE, messageCreatedValue.getType().toString());
        Assert.assertEquals(NICKNAME1, messageCreatedValue.getNickName());

        AppMessage message = messageCaptor.getValue();
        Assert.assertEquals(MESSAGE_ID_1, message.getId());
        Assert.assertNull("should be null", message.getUserName());
        Assert.assertNull("should be null", message.getUserId());
        Assert.assertEquals(ADD_USER_TYPE, message.getType().toString());
        Assert.assertEquals(NICKNAME1, message.getNickName());
        Assert.assertTrue(message.getMsgContext().toString().contains(MESSAGE_CONTEXT_NEW_USER));

    }

    @Test
    public void loginNewUser_PublishNewUserPublishUserWithID_Success(){

        ReflectionTestUtils.setField(buildAppMessageService, "MASK_ID_NAME", false);

        restControllerApp.login(user);

        ArgumentCaptor<AppMessage> messageCaptor = ArgumentCaptor.forClass(AppMessage.class);
        ArgumentCaptor<IChatUserDTO> userRepoCaptor = ArgumentCaptor.forClass(IChatUserDTO.class);
        ArgumentCaptor<IAppMessageDTO> messageRepoCaptor = ArgumentCaptor.forClass(IAppMessageDTO.class);

        verify(publishMessageMock, times(1)).publish(messageCaptor.capture());
        verify(userRepositoryMock, times(1)).insert(userRepoCaptor.capture());
        verify(messageRepositoryMock, times(1)).insert(messageRepoCaptor.capture());

        IAppMessageDTO messageCreatedValue = messageRepoCaptor.getValue();

        Assert.assertEquals(USER_ID1, messageCreatedValue.getUserId());
        Assert.assertEquals(USER_NAME1, messageCreatedValue.getUserName());
        Assert.assertEquals(NICKNAME1, messageCreatedValue.getNickName());

        Assert.assertNull("message id should be null", messageCreatedValue.getId());
        Assert.assertEquals(NEW_USER_TYPE, messageCreatedValue.getType().toString());
        Assert.assertEquals(NICKNAME1, messageCreatedValue.getNickName());
    }

    @Test
    public void loginNewUser_noNickName_Success(){

        user.setNickName(null);
        userDTO.setNickName(userDTO.getUserName());

        ResponseEntity<ChatUser> user1 = restControllerApp.login(user);

        verify(publishMessageMock, times(1)).publish(messageCaptor.capture());
        verify(userRepositoryMock, times(1)).insert(userRepoCaptor.capture());
        verify(messageRepositoryMock, times(1)).insert(messageRepoCaptor.capture());

        IChatUserDTO userCreatedTDO = userRepoCaptor.getValue();
        Assert.assertNull("user id should be null", userCreatedTDO.getId());
        Assert.assertEquals(USER_NAME1, userCreatedTDO.getUserName());
        Assert.assertEquals(PASSWORD1, userCreatedTDO.getPassword());
        Assert.assertEquals(USER_NAME1, userCreatedTDO.getNickName());

        IAppMessageDTO messageCreatedValue = messageRepoCaptor.getValue();

        Assert.assertNull("should be null", messageCreatedValue.getUserId());
        Assert.assertNull("should be null", messageCreatedValue.getUserName());
        Assert.assertEquals(USER_NAME1, messageCreatedValue.getNickName());

        Assert.assertNull("message id should be null", messageCreatedValue.getId());
        Assert.assertEquals(NEW_USER_TYPE, messageCreatedValue.getType().toString());
        Assert.assertEquals(USER_NAME1, messageCreatedValue.getNickName());

        AppMessage message = messageCaptor.getValue();
        Assert.assertEquals(MESSAGE_ID_1, message.getId());
        Assert.assertNull("should be null", message.getUserName());
        Assert.assertNull("should be null", message.getUserId());
        Assert.assertEquals(ADD_USER_TYPE, message.getType().toString());
        Assert.assertEquals(NICKNAME1, message.getNickName());
        Assert.assertTrue(message.getMsgContext().toString().contains(MESSAGE_CONTEXT_NEW_USER));
    }

    @Test
    public void loginExistUser_fail() {

        when(userRepositoryMock.getByUserName(any())).thenReturn(userDTO);

        try {
            restControllerApp.login(user);
            Assert.fail();
        }catch (BadRequestException ex){
            Assert.assertTrue("expected: "+ USER_NAME_ALREADY_EXIST_MESSAGE + ", get: " + ex.getMessage(),
                    ex.getMessage().contains(USER_NAME_ALREADY_EXIST_MESSAGE));
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
