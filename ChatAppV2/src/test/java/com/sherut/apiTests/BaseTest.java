package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.messaging.interfaces.IPublishMessageGWService;
import com.sherut.models.DTO.implementations.AppMessageDTO;
import com.sherut.models.DTO.implementations.ChatUserDTO;
import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import com.sherut.models.enums.AppMessageTypeENUM;
import com.sherut.repository.interfaces.IMessageRepository;
import com.sherut.repository.interfaces.IUserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@TestPropertySource("classpath:application-test.yaml")
@SpringBootTest
public class BaseTest {

    @Autowired
    protected RestControllerApp restControllerApp;

    @MockBean
    protected IPublishMessageGWService publishMessageMock;

    @MockBean
    protected IUserRepository userRepositoryMock;

    @MockBean
    protected IMessageRepository messageRepositoryMock;

    String PREF = "User_";

    String USER_NAME1 = "user1";
    String USER_ID1 = "userId1";
    String PASSWORD1 = "pass1";
    String NICKNAME1 = "nickName1";

    String USER_NAME2 = "user2";
    String USER_ID2 = "userId2";
    String PASSWORD2 = "pass1";
    String NICKNAME2 = "nickName2";

    String MESSAGE_CONTEXCT1 = "message1";

    String ADMIN = "admin";

    String TOPIC_NAME = "ChatTopic";

    String MESSAGE_TYPE = "MESSAGE";
    String NEW_USER_TYPE = "ADD_USER";
    String REMOVE_USER_TYPE = "REMOVE_USER";
    String ADD_USER_TYPE = "ADD_USER";

    String MESSAGE_ID_1 = "messageID1";
    String MESSAGE_CONTEXT_NEW_USER = "add new user";

    String NO_VALID_PASSWORD_MESSAGE = "not valid password";
    String NO_VALID_USERNAME_MESSAGE = "not valid user name";
    String NO_VALID_NICKNAME_MESSAGE = "not valid nick name";
    String NO_VALID_NAME_MESSAGE = "not valid name";
    String USER_NAME_ALREADY_EXIST_MESSAGE = "user name already exist";
    String NICK_NAME_ALREADY_EXIST_MESSAGE = "nick name already exist";
    String FAIL_REMOVE_USER_MESSAGE = "fail remove user. not found";

    @Before
    public void init() {

        Mockito.when(userRepositoryMock.getById(any())).thenReturn(buildUser());
    }

    protected IChatUserDTO buildUser() {

        return buildUser(USER_NAME1, USER_ID1, PASSWORD1, NICKNAME1);
    }

    protected IChatUserDTO buildUser(String userName, String id, String password, String nickName) {

        IChatUserDTO chatUser = new ChatUserDTO(userName);
        chatUser.setId(id);
        chatUser.setUserName(userName);
        chatUser.setNickName(nickName);
        chatUser.setPassword(password);

        return chatUser;
    }

    protected List<IChatUserDTO> buildAllUsers() {
        IChatUserDTO chatUser1 = buildUser(USER_NAME1, USER_ID1, PASSWORD1, NICKNAME1);
        IChatUserDTO chatUser2 = buildUser(USER_NAME2, USER_ID2, PASSWORD2, NICKNAME2);
        List<IChatUserDTO> chatUsers = new ArrayList<>();
        chatUsers.add(chatUser1);
        chatUsers.add(chatUser2);

        return chatUsers;
    }

    protected IAppMessageDTO buildMessage() {
        return buildMessage(MESSAGE_ID_1, USER_ID1, USER_NAME1, NICKNAME1, AppMessageTypeENUM.MESSAGE, MESSAGE_CONTEXCT1);

    }

    protected IAppMessageDTO buildMessage(String id, String userId, String userName, String nickName, AppMessageTypeENUM type, String MESSAGE_CONTEXCT) {
        IAppMessageDTO appMessage = new AppMessageDTO();
        appMessage.setId(id);
        appMessage.setUserId(userId);
        appMessage.setUserName(userName);
        appMessage.setNickName(nickName);
        appMessage.setType(type);
        appMessage.setMsgContext(MESSAGE_CONTEXCT);

        return appMessage;
    }


}
