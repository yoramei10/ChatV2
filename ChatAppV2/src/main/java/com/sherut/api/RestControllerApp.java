package com.sherut.api;

import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.mappers.interfaces.IMapAppMessageToMessagingAppMessage;
import com.sherut.mappers.interfaces.IMapChatUserToChatUserDM;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.ResourceModels.TopicParams;
import com.sherut.services.applicationServices.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RestControllerApp {

    @Autowired
    private ILoginApplicationService login;
    @Autowired
    private IGetAllNickNamesApplicationService getAllNickNames;
    @Autowired
    private IGetAllUsersApplicationService getAllUsers;
    @Autowired
    private IRemoveUserApplicationService removeUserApplicationService;
    @Autowired
    private IPublishMessageApplicationService publishMessageApplicationService;
    @Autowired
    private IMapChatUserToChatUserDM mapChatUserToChatUserDM;
    @Autowired
    private IMapAppMessage mapAppMessage;
    @Autowired
    private IMapAppMessageToMessagingAppMessage mapAppMessageToMessagingAppMessage;
    @Autowired
    private IGetTopicApplicationService getTopicApplicationService;
    @Autowired
    private IGetAllMessagesApplicationService getAllMessagesApplicationService;

    @Value("${url.baseUrl}")
    String BASEAPI1;
    final String BASEAPI = "/chatApp";


    @RequestMapping(value = BASEAPI + "/login",
            produces = "application/json",
            method = RequestMethod.POST)
    public ResponseEntity<ChatUser> login(@RequestParam(value = "userName") @PathVariable("userName") String userName,
                                          @RequestParam(value = "password") @PathVariable("password") String password,
                                          @RequestParam(value = "nickName", required = false) @PathVariable("name") String nickName) {

        ChatUser user = login.loginApp(userName, password, nickName);

            return new ResponseEntity<ChatUser>(user, HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI + "/logout",
            produces = "application/json",
            method = RequestMethod.DELETE)
    public ResponseEntity<String> removeUser(@RequestParam(value = "id") @PathVariable("id") String id) {

        ChatUser userToRemove = removeUserApplicationService.removeUser(id);

        return new ResponseEntity<String>(String.format("user %s was removed", userToRemove.getName()), HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI + "/getAllNickNames",
            produces = "application/json",
            method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllNickNames(@RequestParam(value = "id") @PathVariable("id") String id) {

        List<String> allNickNames = getAllNickNames.getAllNickNames(id);

        return new ResponseEntity<List<String>>(allNickNames, HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI + "/getAllUsers",
            produces = "application/json",
            method = RequestMethod.GET)
    public ResponseEntity<List<ChatUser>>getAllUsers(@RequestParam(value = "id") @PathVariable("id") String id) {

        List<ChatUser> allUsers = getAllUsers.getAllUsers(id);

        return new ResponseEntity<List<ChatUser>>(allUsers, HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI + "/publishNewMessage",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST)
    public ResponseEntity<String> publishNewMessage(@RequestParam(value = "id") @PathVariable("id") String id,
                                            @RequestBody AppMessage appMessage) {

        publishMessageApplicationService.publish(id, appMessage);

        return new ResponseEntity<String>( "message was published successfully", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = BASEAPI + "/getTopic",
            produces = "application/json",
            method = RequestMethod.GET)
    public ResponseEntity<TopicParams> getTopic() {

        TopicParams topicParams = getTopicApplicationService.getTopicParams();
        return new ResponseEntity<TopicParams>(topicParams, HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI + "/getAllMessages",
            produces = "application/json",
            method = RequestMethod.GET)
    public ResponseEntity<List<AppMessage>> getAllMessages(@RequestParam(value = "id") @PathVariable("id") String id) {

        List<AppMessage> aLlMessages = getAllMessagesApplicationService.getALlMessages(id);

        return new ResponseEntity<List<AppMessage>>(aLlMessages, HttpStatus.OK);
    }

}
