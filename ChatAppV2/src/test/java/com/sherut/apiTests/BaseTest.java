package com.sherut.apiTests;

import com.sherut.api.RestControllerApp;
import com.sherut.messaging.interfaces.IPublishMessageService;
import com.sherut.models.DModels.interfaces.IAllUserDM;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@TestPropertySource("classpath:application-test.yaml")
@SpringBootTest
public class BaseTest {

    @Autowired
    protected RestControllerApp restControllerApp;
    @Autowired
    protected IAllUserDM allUsers;

    @Autowired
    @MockBean
    protected IPublishMessageService publishMessageMock;

    String PREF = "User_";

    String USER_NAME1 = "user1";
    String USER_ID1 = PREF+USER_NAME1+"_1";
    String PASSWORD1 = "user1";
    String NICKNAME1 = "nickName1";

    String USER_NAME2 = "user2";
    String USER_ID2 = PREF+USER_NAME2+"_2";
    String PASSWORD2 = "user2";
    String NICKNAME2 = "nickName2";

    String ADMIN = "admin";

    String TOPIC_NAME = "ChatTopic";

    String MESSAGE_TYPE = "MESSAGE";
    String REMOVE_USER_TYPE = "REMOVE_USER";
    String ADD_USER_TYPE = "ADD_USER";

    String NO_VALID_PASSWORD_MESSAGE = "not valid password";
    String NO_VALID_USERNAME_MESSAGE = "not valid user name";
    String NO_VALID_NICKNAME_MESSAGE = "not valid nick name";
    String NO_VALID_NAME_MESSAGE = "not valid name";
    String FAIL_REMOVE_USER_MESSAGE = "fail remove user. not found";

        protected ChatUser buildUser(String userName, String id, String password, String nickName){

        ChatUser chatUser = new ChatUser();
        chatUser.setName(userName);
        chatUser.setNickName(nickName);
        chatUser.setPassword(password);
        chatUser.setId(id);

        return chatUser;
    }

    protected List<ChatUser> buildAllUsers(){
        ChatUser chatUser1 = buildUser(USER_NAME1, USER_ID1, PASSWORD1, NICKNAME1);
        ChatUser chatUser2 = buildUser(USER_NAME2, USER_ID2, PASSWORD2, NICKNAME2);
        List<ChatUser> chatUsers = new ArrayList<>();
        chatUsers.add(chatUser1);
        chatUsers.add(chatUser2);

        return chatUsers;
    }

    protected AppMessage buildMessage(String USER_ID1, String USER_NAME1, String ADD_USER_TYPE, String MESSAGE_CONTEXCT){
        AppMessage appMessage = new AppMessage();
        appMessage.setId(USER_ID1);
        appMessage.setNickName(USER_NAME1);
        appMessage.setType(ADD_USER_TYPE);
        appMessage.setMsgContext(MESSAGE_CONTEXCT);

        return appMessage;
    }
}
